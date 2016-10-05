CREATE OR REPLACE FUNCTION "updating_<rule_id>"() RETURNS trigger
 LANGUAGE plpgsql
 AS $$
declare 

 boolantnew boolean;
 boolantold boolean;
 boolconnew boolean;
 boolconold boolean;

BEGIN

 if (<antecedent_change_UPDATE>) then 
  if (<consequent_change_UPDATE>) then

    boolantold := (<ant_rule_old_expr>) and (<ant_rule_old_not_null>);
    boolantnew := (<ant_rule_new_expr>) and (<ant_rule_new_not_null>);
    boolconnew := (<con_rule_new_expr>) and (<con_rule_new_not_null>); 
    boolconold := (<con_rule_old_expr>) and (<con_rule_old_not_null>);

   if (not(boolantold) and boolantnew) then update data_rules set sa = sa+1 where rule_id='<rule_id>'; 
   else
     if (boolantold and not(boolantnew)) then update data_rules set sa = sa-1 where rule_id='<rule_id>'; 
     end if;
   end if; 
   if (not(boolconold) and boolconnew) then update data_rules set sc = sc+1 where rule_id='<rule_id>'; 
   else
     if (boolconold and not(boolconnew)) then update data_rules set sc = sc-1 where rule_id='<rule_id>'; 
     end if;
   end if; 
   if (not(boolantold and boolconold) and (boolantnew and boolconnew)) then update data_rules set sac = sac+1 where rule_id='<rule_id>'; 
   else 
     if ((boolantold and boolconold) and not(boolantnew and boolconnew)) then update data_rules set sac = sac-1 where rule_id='<rule_id>'; 
     end if;
   end if; 
   else 
    
    boolantold := (<ant_rule_old_expr>) and (<ant_rule_old_not_null>);
    boolantnew := (<ant_rule_new_expr>) and (<ant_rule_new_not_null>);
    boolconnew := (<con_rule_new_expr>) and (<con_rule_new_not_null>);

    if (not(boolantold) and boolantnew) then 
     if (boolconnew) then update data_rules set sac = sac+1, sa = sa+1 where rule_id='<rule_id>'; 
     else update data_rules set sa = sa+1 where rule_id='<rule_id>';
     end if;
    else 
     if (boolantold and not(boolantnew)) then 
      if (boolconnew) then update data_rules set sac = sac-1, sa = sa-1 where rule_id='<rule_id>'; 
      else update data_rules set sa = sa-1 where rule_id='<rule_id>';
      end if; 
     end if; 
    end if; 
   end if; 
 else 
  if (<consequent_change_UPDATE>) then
   boolconnew := (<con_rule_new_expr>) and (<con_rule_new_not_null>); 
   boolconold := (<con_rule_old_expr>) and (<con_rule_old_not_null>);
   boolantnew := (<ant_rule_new_expr>) and (<ant_rule_new_not_null>);

   if (not(boolconold) and boolconnew) then 
    if (boolantnew) then update data_rules set sac = sac+1, sc = sc+1 where rule_id='<rule_id>'; 
    else update data_rules set sc = sc+1 where rule_id='<rule_id>';
    end if;
   else 
    if (boolconold and not(boolconnew)) then
     if (boolantnew) then update data_rules set sac = sac-1, sc = sc-1 where rule_id='<rule_id>'; 
     else update data_rules set sc = sc-1 where rule_id='<rule_id>';
     end if; 
    end if; 
   end if;
  end if;
 end if;

 return null;
END $$;


CREATE OR REPLACE FUNCTION "deleting_<rule_id>"() RETURNS trigger
 LANGUAGE plpgsql
 AS $$
BEGIN

 perform setval('"count_row_<table_name>"', nextval('"count_row_<table_name>"')-2);

 if (<ant_rule_old_expr>) then 
  if (<con_rule_old_expr>) then 
   update data_rules set sac = sac-1, sa = sa-1, sc = sc - 1 where rule_id='<rule_id>'; 
  else 
   update data_rules set sa = sa-1 where rule_id='<rule_id>';
  end if;
  else if (<con_rule_old_expr>) then
   update data_rules set sc = sc-1 where rule_id='<rule_id>'; 
  end if;
 end if;
 
 return null;
END$$;


CREATE OR REPLACE FUNCTION "inserting_<rule_id>"() RETURNS trigger
 LANGUAGE plpgsql
 AS $$ 
BEGIN

 perform nextval('"count_row_<table_name>"');

 if (<ant_rule_new_expr>) then 
  if (<con_rule_new_expr>) then 
   update data_rules set sac = sac+1, sa = sa+1, sc = sc+1 where rule_id='<rule_id>'; 
  else 
   update data_rules set sa = sa+1 where rule_id='<rule_id>';
  end if;
 else if (<con_rule_new_expr>) then   
  update data_rules set sc = sc+1 where rule_id='<rule_id>'; 
  end if;
 end if;

 return null;
END$$;


CREATE OR REPLACE FUNCTION "measures_update_<rule_id>"() RETURNS void
 LANGUAGE plpgsql
 AS $$
declare 
 
 nva bigint;
 nvc bigint;
 nvr bigint;
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
INSERT INTO data_rules values ('<rule_id>','<ant_rule_expr_xx>','<con_rule_expr_xx>',(select count(*) from "<table_name>" where <ant_rule_expr>),(select count(*) from "<table_name>" where <con_rule_expr>),(select count(*) from "<table_name>" where <ant_rule_expr> and <con_rule_expr>),0);


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