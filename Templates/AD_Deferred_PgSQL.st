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
 recd RECORD; 
 sal BIGINT;
 scl BIGINT;
 srl BIGINT;
 cou BIGINT;
 
BEGIN
 
 select into cou power(setval('"count_row_<table_name>"', nextval('"count_row_<table_name>"')-1 + (select count(*) from "it_<table_name>") - (select count(*) from "dt_<table_name>")),2); 

 FOR recd IN SELECT <ant_att_list>,<con_att_list> FROM "it_<table_name>"
 LOOP

  if (<ant_rule_recd_not_null>) then 
   update "ad_<rule_id>_sa" set support = support + 1 where <ant_recd_match>;
   if (not FOUND) then 
    insert into "ad_<rule_id>_sa" values (<ant_att_list_recd>,1);
   end if;
   if (<con_rule_recd_not_null>) then
    update "ad_<rule_id>_sc" set support = support + 1 where <con_recd_match>;
    if (not FOUND) then 
     insert into "ad_<rule_id>_sc" values (<con_att_list_recd>,1);
    end if;
    update "ad_<rule_id>_sac" set support = support + 1 where <ant_recd_match> and <con_recd_match>;
    if (not FOUND) then 
     insert into "ad_<rule_id>_sac" values (<ant_att_list_recd>,<con_att_list_recd>,1);
    end if; 
   end if;
  else if (<con_rule_recd_not_null>) then
    update "ad_<rule_id>_sc" set support = support + 1 where <con_recd_match>;
    if (not FOUND) then 
     insert into "ad_<rule_id>_sc" values (<con_att_list_recd>,1);
    end if;
    end if; 
  end if;
 end LOOP;

 FOR recd IN SELECT <ant_att_list>,<con_att_list> FROM "dt_<table_name>"
 LOOP

  if (<ant_rule_recd_not_null>) then 
   update "ad_<rule_id>_sa" set support = support - 1 where <ant_recd_match>;
   if (not FOUND) then 
    insert into "ad_<rule_id>_sa" values (<ant_att_list_recd>,1);
   end if;
   if (<con_rule_recd_not_null>) then
    update "ad_<rule_id>_sc" set support = support - 1 where <con_recd_match>;
    if (not FOUND) then 
     insert into "ad_<rule_id>_sc" values (<con_att_list_recd>,1);
    end if;
    update "ad_<rule_id>_sac" set support = support - 1 where <ant_recd_match> and <con_recd_match>;
    if (not FOUND) then 
     insert into "ad_<rule_id>_sac" values (<ant_att_list_recd>,<con_att_list_recd>,1);
    end if; 
   end if;
  else if (<con_rule_recd_not_null>) then
    update "ad_<rule_id>_sc" set support = support - 1 where <con_recd_match>;
    if (not FOUND) then 
     insert into "ad_<rule_id>_sc" values (<con_att_list_recd>,1);
    end if;
    end if; 
  end if;
 end LOOP;

 select sum(power(support,2)) into sal from "ad_<rule_id>_sa";
 select sum(power(support,2)) into scl from "ad_<rule_id>_sc";
 select sum(power(support,2)) into srl from "ad_<rule_id>_sac";

  if (sal\<>0 and cou\<>0) then 
  if (srl::double precision/sal::double precision > scl::double precision/cou::double precision) then
    if (1::double precision - scl::double precision/cou::double precision \<> 0) then
      update data_rules set sa=sal,sc=scl,sac=srl, cf=(srl::double precision/sal::double precision - scl::double precision/cou::double precision) / (1::double precision - scl::double precision/cou::double precision) where rule_id='<rule_id>';
	end if;  
  else
    if (scl \<> 0) then
	  update data_rules set sa=sal,sc=scl,sac=srl, cf=(srl::double precision/sal::double precision - scl::double precision/cou::double precision) / (scl::double precision/cou::double precision) where rule_id='<rule_id>'; 
	end if;  
  end if;
 end if;

 delete from "dt_<table_name>";
 delete from "it_<table_name>";
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
INSERT INTO data_rules values ('<rule_id>','<ant_rule_expr_xx>','<con_rule_expr_xx>',(select sum(power(b.a,2)) from (select count(*) as a from "<table_name>" where <ant_rule_not_null> group by <ant_att_list>) as b),(select sum(power(b.a,2)) from (select count(*) as a from "<table_name>" where <con_rule_not_null> group by <con_att_list>) as b),(select sum(power(b.a,2)) from (select count(*) as a from "<table_name>" where <ant_rule_not_null> and <con_rule_not_null> group by <ant_att_list>,<con_att_list>) as b),0);


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
 
 
<create_table_sa>


<create_table_sc>


<create_table_sac>


<create_table_dt>


<create_table_it>


DROP TRIGGER IF EXISTS tdelete ON "<table_name>";
DROP TRIGGER IF EXISTS tinsert ON "<table_name>";
DROP TRIGGER IF EXISTS tupdate ON "<table_name>";
 
CREATE TRIGGER tdelete AFTER DELETE ON "<table_name>" FOR EACH ROW EXECUTE PROCEDURE deleting();
CREATE TRIGGER tinsert AFTER INSERT ON "<table_name>" FOR EACH ROW EXECUTE PROCEDURE inserting();
CREATE TRIGGER tupdate AFTER UPDATE ON "<table_name>" FOR EACH ROW EXECUTE PROCEDURE updating();