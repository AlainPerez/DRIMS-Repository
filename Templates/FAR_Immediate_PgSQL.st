CREATE OR REPLACE FUNCTION "updating_<rule_id>"() RETURNS trigger
 LANGUAGE plpgsql
 AS $$

BEGIN

 if (<antecedent_change_UPDATE>) then 
  if (<consequent_change_UPDATE>) then
   update data_rules set sa = sa-<ant_FAR_list_old>+<ant_FAR_list_new>, sc = sc-<con_FAR_list_old>+<con_FAR_list_new>, sac = sac-<ant_con_FAR_list_old>+<ant_con_FAR_list_new> where rule_id='<rule_id>';
  else 
   update data_rules set sa = sa-<ant_FAR_list_old>+<ant_FAR_list_new>, sac = sac-<ant_con_FAR_list_old>+<ant_con_FAR_list_new> where rule_id='<rule_id>';
  end if;
 else 
  if (<consequent_change_UPDATE>) then
   update data_rules set sc = sc-<con_FAR_list_old>+<con_FAR_list_new>, sac = sac-<ant_con_FAR_list_old>+<ant_con_FAR_list_new> where rule_id='<rule_id>';
  end if;
 end if;

 return null;
END$$;


CREATE OR REPLACE FUNCTION "deleting_<rule_id>"() RETURNS trigger
 LANGUAGE plpgsql
 AS $$

BEGIN

 perform setval('"count_row_<table_name>"', nextval('"count_row_<table_name>"')-2);

 update data_rules set sac = sac-<ant_con_FAR_list_old>, sa = sa-<ant_FAR_list_old>, sc = sc-<con_FAR_list_old> where rule_id='<rule_id>'; 

 return null;
END$$;


CREATE OR REPLACE FUNCTION "inserting_<rule_id>"() RETURNS trigger
 LANGUAGE plpgsql
 AS $$ 
BEGIN

 perform nextval('"count_row_<table_name>"');

 update data_rules set sac = sac+<ant_con_FAR_list_new>, sa = sa+<ant_FAR_list_new>, sc = sc+<con_FAR_list_new> where rule_id='<rule_id>'; 

 return null;
END$$;


CREATE OR REPLACE FUNCTION "measures_update_<rule_id>"() RETURNS void
 LANGUAGE plpgsql
 AS $$
declare 
 
 nva double precision;
 nvc double precision;
 nvr double precision;
 cou bigint;


BEGIN

 select into cou setval('"count_row_<table_name>"', nextval('"count_row_<table_name>"')-1);

 select into nva,nvc,nvr sa,sc,sac from data_rules where rule_id='<rule_id>';

  if (nva \<> 0 and cou \<> 0) then 
  if (nvr::double precision/nva::double precision > nvc::double precision/cou::double precision) then
	if ((1::double precision - nvc::double precision/cou::double precision) \<> 0) then
	  update data_rules set cf = (nvr::double precision / nva::double precision - nvc::double precision/cou::double precision) / (1::double precision - nvc::double precision/cou::double precision) where rule_id='<rule_id>';
	end if;
  else if (nvc \<> 0) then
	     update data_rules set cf = (nvr::double precision / nva::double precision - nvc::double precision/cou::double precision) / (nvc::double precision/cou::double precision) where rule_id='<rule_id>'; 
       end if;
  end if;
 end if;

END$$;


<create_FAR_functions>


CREATE TABLE IF NOT EXISTS data_rules (
 rule_id character varying NOT NULL,
 antecedent character varying,
 consequent character varying,
 sa bigint,
 sc bigint,
 sac bigint,
 cf double precision,
 CONSTRAINT "Data_Rules_pkey" PRIMARY KEY (rule_id)
);


DELETE FROM data_rules WHERE rule_id = '<rule_id>';
INSERT INTO data_rules values ('<rule_id>','<ant_rule_expr_xx>','<con_rule_expr_xx>',(select sum(<ant_FAR_list>) from "<table_name>"),(select sum(<con_FAR_list>) from "<table_name>"),(select sum(<ant_con_FAR_list>) from "<table_name>"),0);


CREATE OR REPLACE VIEW rules_certainty_factor AS
 SELECT rule_id, antecedent, consequent, cf as certainty_factor
 FROM data_rules
 ORDER BY rule_id;


DROP SEQUENCE IF EXISTS "count_row_<table_name>";
CREATE SEQUENCE "count_row_<table_name>"
 START WITH 0
 INCREMENT BY 1
 MINVALUE 0
 NO MAXVALUE
 CACHE 1;
SELECT setval('"count_row_<table_name>"', (select count(*) from "<table_name>"));
 
 
DROP TRIGGER IF EXISTS "tdelete_<rule_id>" ON "<table_name>";
DROP TRIGGER IF EXISTS "tinsert_<rule_id>" ON "<table_name>";
DROP TRIGGER IF EXISTS "tupdate_<rule_id>" ON "<table_name>";
 
 
CREATE TRIGGER "tdelete_<rule_id>" AFTER DELETE ON "<table_name>" FOR EACH ROW EXECUTE PROCEDURE "deleting_<rule_id>"();
CREATE TRIGGER "tinsert_<rule_id>" AFTER INSERT ON "<table_name>" FOR EACH ROW EXECUTE PROCEDURE "inserting_<rule_id>"();
CREATE TRIGGER "tupdate_<rule_id>" AFTER UPDATE ON "<table_name>" FOR EACH ROW EXECUTE PROCEDURE "updating_<rule_id>"();