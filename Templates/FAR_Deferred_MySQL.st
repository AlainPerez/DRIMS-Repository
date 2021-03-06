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

 update `count_row_<table_name>` set count=(count + (select count(*) from `it_<table_name>`) - (select count(*) from `dt_<table_name>`)) LIMIT 1;
 select count into @cou FROM `count_row_<table_name>`; 
 
 if ((select count(*) from `it_<table_name>` limit 1) = 0) then set @itb = false; set @aux1 = 0; else set @itb = true; end if;
 if ((select count(*) from `dt_<table_name>` limit 1) = 0) then set @dtb = false; set @aux2 = 0; else set @dtb = true; end if; 

 if (@itb) then set @aux1 = (select sum(<ant_FAR_list>) from `it_<table_name>`); end if; 
 if (@dtb) then set @aux2 = (select sum(<ant_FAR_list>) from `dt_<table_name>`); end if;
 set @nva = @aux1 - @aux2;

 if (@itb) then set @aux1 = (select sum(<con_FAR_list>) from `it_<table_name>`); end if; 
 if (@dtb) then set @aux2 = (select sum(<con_FAR_list>) from `dt_<table_name>`); end if;
 set @nvc = @aux1 - @aux2;
  
 if (@itb) then set @aux1 = (select sum(<ant_con_FAR_list>) from `it_<table_name>`); end if; 
 if (@dtb) then set @aux2 = (select sum(<ant_con_FAR_list>) from `dt_<table_name>`); end if;
 set @nvr = @aux1 - @aux2;

 update data_rules set sa=sa+@nva, sc=sc+@nvc, sac=sac+@nvr where rule_id='<rule_id>';

 select sa,sc,sac into @nva,@nvc,@nvr from data_rules where rule_id='<rule_id>';
 
 if (@nva \<> 0 and @cou \<> 0) then  
  if (@nvr/@nva > @nvc/@cou) then
   if (1 - @nvc/@cou \<> 0) then
     update data_rules set cf = (@nvr / @nva - @nvc/@cou) / (1 - @nvc/@cou) where rule_id='<rule_id>';
   end if;
  else
   if (@nvc/@cou \<> 0) then
     update data_rules set cf = (@nvr / @nva - @nvc/@cou) / (@nvc/@cou) where rule_id='<rule_id>'; 
   end if; 
  end if;
 end if;
 
 delete from `it_<table_name>`;
 delete from `dt_<table_name>`;
END;


<create_FAR_functions>


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
INSERT INTO data_rules values ('<rule_id>','<ant_rule_expr_xx>','<con_rule_expr_xx>',(select sum(<ant_FAR_list>) from `<table_name>`),(select sum(<con_FAR_list>) from `<table_name>`),(select sum(<ant_con_FAR_list>) from `<table_name>`),0);


DROP VIEW IF EXISTS `rules_certainty_factor`;
CREATE VIEW `rules_certainty_factor` AS 
select `rule_id`,`antecedent`,`consequent`,`cf` 
 from `data_rules` 
 order by `rule_id`;


DROP TABLE IF EXISTS `dt_<table_name>`;
<create_table_dt>


DROP TABLE IF EXISTS `it_<table_name>`;
<create_table_it>