DROP TRIGGER IF EXISTS `updating`;
CREATE TRIGGER `updating` AFTER UPDATE ON `<table_name>` FOR EACH ROW 

BEGIN
 update `it_<table_name>` set <all_match_new> where <pk_list_new_match>; 
 if (ROW_COUNT() = 0) then 
  insert into `it_<table_name>` values (<table_att_list_new>);
  insert into `dt_<table_name>` values (<table_att_list_old>);
 end if; 
END;

DROP TRIGGER IF EXISTS `deleting`;
CREATE TRIGGER `deleting` AFTER DELETE ON `<table_name>` FOR EACH ROW 

BEGIN

 delete from `it_<table_name>` where <pk_list_old_match>; 
 if (ROW_COUNT() = 0) then
  insert into `dt_<table_name>` values (<table_att_list_old>);
 end if; 
END;

DROP TRIGGER IF EXISTS `inserting`;
CREATE TRIGGER `inserting` AFTER INSERT ON `<table_name>` FOR EACH ROW 

BEGIN

 insert into `it_<table_name>` values (<table_att_list_new>);
END;


DROP PROCEDURE IF EXISTS `measures_update_<rule_id>`;
CREATE PROCEDURE `measures_update_<rule_id>`()

BEGIN

 DECLARE done BOOLEAN DEFAULT FALSE;
 <AD_att_Rule_DEC>
 DECLARE sal,scl,srl,cou BIGINT;
 DECLARE cur_it CURSOR FOR SELECT <ant_att_list>,<con_att_list> FROM `it_<table_name>`;
 DECLARE cur_dt CURSOR FOR SELECT <ant_att_list>,<con_att_list> FROM `dt_<table_name>`;
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
 
 update `count_row_<table_name>` set count=(count + (select count(*) from `it_<table_name>`) - (select count(*) from `dt_<table_name>`)) LIMIT 1;
 select POWER(count,2) FROM `count_row_<table_name>` limit 1 into cou; 

 OPEN cur_it; 
  read_loop: LOOP
   FETCH cur_it INTO <AD_ant_att_list>, <AD_con_att_list>;
   IF done THEN
    LEAVE read_loop;
   END IF;

   if (<AD_ant_att_not_null>) then 
     update `ad_<rule_id>_sa` set support = support + 1 where <AD_ant_att_match>;
     if (ROW_COUNT() = 0) then 
      insert into `ad_<rule_id>_sa` values (<AD_ant_att_list>,1);
     end if;
     if (<AD_con_att_not_null>) then
      update `ad_<rule_id>_sc` set support = support + 1 where <AD_con_att_match>;
      if (ROW_COUNT() = 0) then 
       insert into `ad_<rule_id>_sc` values (<AD_con_att_list>,1);
      end if;
      update `ad_<rule_id>_sac` set support = support + 1 where <AD_ant_att_match> and <AD_con_att_match>;
      if (ROW_COUNT() = 0) then 
       insert into `ad_<rule_id>_sac` values (<AD_ant_att_list>,<AD_con_att_list>,1);
      end if; 
     end if;
    else if (<AD_con_att_not_null>) then
      update `ad_<rule_id>_sc` set support = support + 1 where <AD_con_att_match>;
      if (ROW_COUNT() = 0) then 
       insert into `ad_<rule_id>_sc` values (<AD_con_att_list>,1);
      end if;
      end if; 
    end if;
  END LOOP;
 CLOSE cur_it;

 set done = false;
 OPEN cur_dt; 
  read_loop: LOOP
   FETCH cur_dt INTO <AD_ant_att_list>, <AD_con_att_list>;
   IF done THEN
    LEAVE read_loop;
   END IF;

   if (<AD_ant_att_not_null>) then 
    update `ad_<rule_id>_sa` set support = support - 1 where <AD_ant_att_match>;
    if (ROW_COUNT() = 0) then 
     insert into `ad_<rule_id>_sa` values (<AD_ant_att_list>,1);
    end if;
    if (<AD_con_att_not_null>) then
     update `ad_<rule_id>_sc` set support = support - 1 where <AD_con_att_match>;
     if (ROW_COUNT() = 0) then 
      insert into `ad_<rule_id>_sc` values (<AD_con_att_list>,1);
     end if;
     update `ad_<rule_id>_sac` set support = support - 1 where <AD_ant_att_match> and <AD_con_att_match>;
     if (ROW_COUNT() = 0) then 
      insert into `ad_<rule_id>_sac` values (<AD_ant_att_list>,<AD_con_att_list>,1);
     end if; 
    end if;
   else if (<AD_con_att_not_null>) then
     update `ad_<rule_id>_sc` set support = support - 1 where <AD_con_att_match>;
     if (ROW_COUNT() = 0) then 
      insert into `ad_<rule_id>_sc` values (<AD_con_att_list>,1);
     end if;
     end if; 
   end if;
  END LOOP;
 CLOSE cur_dt;


 select sum(power(support,2)) into sal from `ad_<rule_id>_sa`;
 select sum(power(support,2)) into scl from `ad_<rule_id>_sc`;
 select sum(power(support,2)) into srl from `ad_<rule_id>_sac`;

 if (sal \<> 0 and cou \<> 0) then 
  if (srl/sal > scl/cou) then
    if (1 - scl/cou \<> 0) then
     update data_rules set sa=sal,sc=scl,sac=srl, cf=(srl/sal - scl/cou) / (1 - scl/cou) where rule_id='<rule_id>';
	end if; 
  else
    if (scl/cou \<> 0) then
     update data_rules set sa=sal,sc=scl,sac=srl, fc = (srl/sal - scl/cou) / (scl/cou) where rule_id='<rule_id>';
	end if; 
  end if;
 end if;

 delete from `dt_<table_name>`;
 delete from `it_<table_name>`;
END;

DROP TABLE IF EXISTS `count_row_<table_name>`;
CREATE TABLE `count_row_<table_name>` (
 `count` bigint NOT NULL,
 PRIMARY KEY (`count`)
);
INSERT INTO `count_row_<table_name>` values ((select count(*) from `<table_name>`));


DROP TABLE IF EXISTS `data_rules`;
CREATE TABLE `data_rules` (
 `rule_id` varchar(255) NOT NULL,
 `antecedent` varchar(255),
 `consequent` varchar(255),
 `sa` bigint,
 `sc` bigint,
 `sac` bigint,
 `cf` double,
 PRIMARY KEY (`rule_id`)
);


DELETE FROM data_rules WHERE rule_id = '<rule_id>';
INSERT INTO data_rules values ('<rule_id>','<ant_rule_expr_xx>','<con_rule_expr_xx>',(select sum(power(b.a,2)) from (select count(*) as a from `<table_name>` where <ant_rule_not_null> group by <ant_att_list>) as b),(select sum(power(b.a,2)) from (select count(*) as a from `<table_name>` where <con_rule_not_null> group by <con_att_list>) as b),(select sum(power(b.a,2)) from (select count(*) as a from `<table_name>` where <ant_rule_not_null> and <con_rule_not_null> group by <ant_att_list>,<con_att_list>) as b),0);


DROP TABLE IF EXISTS `ad_<rule_id>_sa`;
<create_table_sa>


DROP TABLE IF EXISTS `ad_<rule_id>_sc`;
<create_table_sc>


DROP TABLE IF EXISTS `ad_<rule_id>_sac`;
<create_table_sac>


DROP TABLE IF EXISTS `dt_<table_name>`;
<create_table_dt>


DROP TABLE IF EXISTS `it_<table_name>`;
<create_table_it>


DROP VIEW IF EXISTS `rules_certainty_factor`;
CREATE VIEW `rules_certainty_factor` AS 
select `rule_id`,`antecedent`,`consequent`,`cf` 
 from `data_rules` 
 order by `rule_id`;