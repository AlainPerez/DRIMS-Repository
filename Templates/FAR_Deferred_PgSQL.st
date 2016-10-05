CREATE OR REPLACE FUNCTION updating() RETURNS trigger
 LANGUAGE plpgsql
 AS $$
BEGIN

 update "it_<table_name>" set <all_match_new> where <pk_list_new_match>;  
 if (NOT FOUND) then 
  insert into "it_<table_name>" select new.*;
  insert into "dt_<table_name>" select old.*;
 end if; 
  
 return null; 
END$$;


CREATE OR REPLACE FUNCTION deleting() RETURNS trigger
 LANGUAGE plpgsql
 AS $$
BEGIN

 delete from "it_<table_name>" where <pk_list_old_match>;
 if not FOUND then
  insert into "dt_<table_name>" select old.*;
 end if; 
  
 return null;
END$$;


CREATE OR REPLACE FUNCTION inserting() RETURNS trigger
 LANGUAGE plpgsql
 AS $$ 
BEGIN

  insert into "it_<table_name>" select new.*; 
   
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
 aux1 double precision;
 aux2 double precision;
 itb boolean;
 dtb boolean;


BEGIN

 select into cou setval('"count_row_<table_name>"', nextval('"count_row_<table_name>"')-1 + (select count(*) from "it_<table_name>") - (select count(*) from "dt_<table_name>")); 
 if ((select count(*) from "it_<table_name>" limit 1) = 0) then itb:=false; aux1 := 0; else itb :=true; end if;
 if ((select count(*) from "dt_<table_name>" limit 1) = 0) then dtb:=false; aux2 := 0; else dtb :=true; end if; 

 if (itb) then aux1 := (select sum(<ant_FAR_list>) from "it_<table_name>"); end if; 
 if (dtb) then aux2 := (select sum(<ant_FAR_list>) from "dt_<table_name>"); end if;
 nva := aux1 - aux2;

 if (itb) then aux1 := (select sum(<con_FAR_list>) from "it_<table_name>"); end if; 
 if (dtb) then aux2 := (select sum(<con_FAR_list>) from "dt_<table_name>"); end if;
 nvc := aux1 - aux2;
  
 if (itb) then aux1 := (select sum(<ant_con_FAR_list>) from "it_<table_name>"); end if; 
 if (dtb) then aux2 := (select sum(<ant_con_FAR_list>) from "dt_<table_name>"); end if;
 nvr := aux1 - aux2;

 update data_rules set sa=sa+nva, sc=sc+nvc, sac=sac+nvr where rule_id='<rule_id>';

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
  
 delete from "it_<table_name>";
 delete from "dt_<table_name>";
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


<create_table_dt>


<create_table_it>


DROP TRIGGER IF EXISTS tdelete ON "<table_name>";
DROP TRIGGER IF EXISTS tinsert ON "<table_name>";
DROP TRIGGER IF EXISTS tupdate ON "<table_name>";
 
 
CREATE TRIGGER tdelete AFTER DELETE ON "<table_name>" FOR EACH ROW EXECUTE PROCEDURE deleting();
CREATE TRIGGER tinsert AFTER INSERT ON "<table_name>" FOR EACH ROW EXECUTE PROCEDURE inserting();
CREATE TRIGGER tupdate AFTER UPDATE ON "<table_name>" FOR EACH ROW EXECUTE PROCEDURE updating();