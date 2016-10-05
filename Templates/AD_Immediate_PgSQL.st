CREATE OR REPLACE FUNCTION "updating_<rule_id>"() RETURNS trigger
 LANGUAGE plpgsql
 AS $$

BEGIN

 if (<antecedent_change_UPDATE>) then 
  if (<consequent_change_UPDATE>) then
   if (<ant_rule_old_not_null>) then 
    update"ad_<rule_id>_sa"set support=support-1 where <ant_old_match>; 
    if (<con_rule_old_not_null>) then 
     update "ad_<rule_id>_sac" set support=support-1 where <ant_old_match> and <con_old_match>;
     update "ad_<rule_id>_sc" set support=support-1 where <con_old_match>; 
    end if; 
   elseif (<con_rule_old_not_null>) then 
    update "ad_<rule_id>_sc" set support=support-1 where <con_old_match>; 
   end if; 

   if (<ant_rule_new_not_null>) then 
    update"ad_<rule_id>_sa"set support=support+1 where <ant_new_match>; 
    if (not FOUND) then
     insert into"ad_<rule_id>_sa"values (<ant_new_list>,1); 
    end if; 
    if (<con_rule_new_not_null>) then
     update "ad_<rule_id>_sac" set support=support+1 where <ant_new_match> and <con_new_match>;
     if (not FOUND) then
      insert into "ad_<rule_id>_sac" values (<ant_new_list>,<con_new_list>,1); 
     end if;
     update "ad_<rule_id>_sc" set support=support+1 where <con_new_match>;
     if (not FOUND) then
      insert into "ad_<rule_id>_sc" values (<con_new_list>,1); 
     end if; 
    end if;
   elseif (<con_rule_new_not_null>) then 
    update "ad_<rule_id>_sc" set support=support+1 where <con_new_match>;
    if (not FOUND) then
     insert into "ad_<rule_id>_sc" values (<con_new_list>,1); 
    end if; 
   end if; 
   
  else 
  
   if (<ant_rule_old_not_null>) then
    update"ad_<rule_id>_sa"set support=support-1 where <ant_old_match>; 
    if (<con_rule_old_not_null>) then 
     update "ad_<rule_id>_sac" set support=support-1 where <ant_old_match> and <con_old_match>;
    end if; 
   end if; 

   if (<ant_rule_new_not_null>) then
    update"ad_<rule_id>_sa"set support=support+1 where <ant_new_match>;
    if (not FOUND) then
     insert into"ad_<rule_id>_sa"values (<ant_new_list>,1); 
    end if; 
    if (<con_rule_new_not_null>) then
     update "ad_<rule_id>_sac" set support=support+1 where <ant_new_match> and <con_new_match>;
     if (not FOUND) then
      insert into "ad_<rule_id>_sac" values (<ant_new_list>,<con_new_list>,1); 
     end if; 
    end if; 
   end if; 

  end if; 
   
 else 
  if (<consequent_change_UPDATE>) then
   if (<con_rule_old_not_null>) then
    update "ad_<rule_id>_sc" set support=support-1 where <con_old_match>; 
    if (<ant_rule_old_not_null>) then 
     update "ad_<rule_id>_sac" set support=support-1 where <ant_old_match> and <con_old_match>;
    end if; 
   end if; 

   if (<con_rule_new_not_null>) then
    update "ad_<rule_id>_sc" set support=support+1 where <con_new_match>; 
    if (not FOUND) then
     insert into "ad_<rule_id>_sc" values (<con_new_list>,1); 
    end if; 
    if (<ant_rule_new_not_null>) then
     update "ad_<rule_id>_sac" set support=support+1 where <ant_new_match> and <con_new_match>;
     if (not FOUND) then
     insert into "ad_<rule_id>_sac" values (<ant_new_list>,<con_new_list>,1); 
     end if; 
    end if;   
   end if; 
  end if;
 end if;

 return null;

END$$;


CREATE OR REPLACE FUNCTION "deleting_<rule_id>"() RETURNS trigger
 LANGUAGE plpgsql
 AS $$
 
BEGIN

 perform setval('"count_row_<table_name>"', nextval('"count_row_<table_name>"')-2);

 if (<ant_rule_old_not_null>) then
  update"ad_<rule_id>_sa"set support=support-1 where <ant_old_match>; 
  if (<con_rule_old_not_null>) then
   update "ad_<rule_id>_sac" set support=support-1 where <ant_old_match> and <con_old_match>;
   update "ad_<rule_id>_sc" set support=support-1 where <con_old_match>; 
  end if; 
 elseif (<con_rule_old_not_null>) then
  update "ad_<rule_id>_sc" set support=support-1 where <con_old_match>;
 end if; 

 return null;
 
END$$;


CREATE OR REPLACE FUNCTION "inserting_<rule_id>"() RETURNS trigger
 LANGUAGE plpgsql
 AS $$ 
BEGIN

perform nextval('"count_row_<table_name>"');

 if (<ant_rule_new_not_null>) then
  update"ad_<rule_id>_sa"set support=support+1 where <ant_new_match>; 
  if (not FOUND) then
   insert into"ad_<rule_id>_sa"values (<ant_new_list>,1); 
  end if; 
  if (<con_rule_new_not_null>) then
   update "ad_<rule_id>_sac" set support=support+1 where <ant_new_match> and <con_new_match>;
   if (not FOUND) then
    insert into "ad_<rule_id>_sac" values (<ant_new_list>,<con_new_list>,1); 
   end if;
   update "ad_<rule_id>_sc" set support=support+1 where <con_new_match>;
   if (not FOUND) then
    insert into "ad_<rule_id>_sc" values (<con_new_list>,1); 
   end if; 
  end if; 
 elseif (<con_rule_new_not_null>) then 
  update "ad_<rule_id>_sc" set support=support+1 where <con_new_match>;
  if (not FOUND) then
   insert into "ad_<rule_id>_sc" values (<con_new_match>,1); 
  end if; 
 end if; 

 return null;
END$$;


CREATE OR REPLACE FUNCTION "measures_update_<rule_id>"() RETURNS void
 LANGUAGE plpgsql
 AS $$
declare 
 
 sal bigint;
 scl bigint;
 srl bigint;
 cou bigint;

BEGIN

 select into cou power(setval('"count_row_<table_name>"', nextval('"count_row_<table_name>"')-1),2);

 select into sal sum(power(support,2)) from "ad_<rule_id>_sa";
 select into scl sum(power(support,2)) from "ad_<rule_id>_sc";
 select into srl sum(power(support,2)) from "ad_<rule_id>_sac";

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


DROP TRIGGER IF EXISTS "tdelete_<rule_id>" ON "<table_name>";
DROP TRIGGER IF EXISTS "tinsert_<rule_id>" ON "<table_name>";
DROP TRIGGER IF EXISTS "tupdate_<rule_id>" ON "<table_name>";
 
CREATE TRIGGER "tdelete_<rule_id>" AFTER DELETE ON "<table_name>" FOR EACH ROW EXECUTE PROCEDURE "deleting_<rule_id>"();
CREATE TRIGGER "tinsert_<rule_id>" AFTER INSERT ON "<table_name>" FOR EACH ROW EXECUTE PROCEDURE "inserting_<rule_id>"();
CREATE TRIGGER "tupdate_<rule_id>" AFTER UPDATE ON "<table_name>" FOR EACH ROW EXECUTE PROCEDURE "updating_<rule_id>"();