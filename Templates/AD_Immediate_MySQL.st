DROP TRIGGER IF EXISTS `updating_<rule_id>`;
CREATE TRIGGER `updating_<rule_id>` AFTER UPDATE ON `<table_name>` FOR EACH ROW 

BEGIN

 if (<antecedent_change_UPDATE>) then 
  if (<consequent_change_UPDATE>) then
   if (<ant_rule_old_not_null>) then 
    update `ad_<rule_id>_sa` set support=support-1 where <ant_old_match>; 
    if (<con_rule_old_not_null>) then 
     update `ad_<rule_id>_sac` set support=support-1 where <ant_old_match> and <con_old_match>;
     update `ad_<rule_id>_sc` set support=support-1 where <con_old_match>; 
    end if; 
   elseif (<con_rule_old_not_null>) then 
    update `ad_<rule_id>_sc` set support=support-1 where <con_old_match>; 
   end if; 

   if (<ant_rule_new_not_null>) then 
    update `ad_<rule_id>_sa` set support=support+1 where <ant_new_match>; 
    if (ROW_COUNT() = 0) then
     insert into `ad_<rule_id>_sa` values (<ant_new_list>,1); 
    end if; 
    if (<con_rule_new_not_null>) then
     update `ad_<rule_id>_sac` set support=support+1 where <ant_new_match> and <con_new_match>;
     if (ROW_COUNT() = 0) then
      insert into `ad_<rule_id>_sac` values (<ant_new_list>,<con_new_list>,1); 
     end if;
     update `ad_<rule_id>_sc` set support=support+1 where <con_new_match>;
     if (ROW_COUNT() = 0) then
      insert into `ad_<rule_id>_sc` values (<con_new_list>,1); 
     end if; 
    end if;
   elseif (<con_rule_new_not_null>) then 
    update `ad_<rule_id>_sc` set support=support+1 where <con_new_match>;
    if (ROW_COUNT() = 0) then
     insert into `ad_<rule_id>_sc` values (<con_new_list>,1); 
    end if; 
   end if; 
   
  else 
  
   if (<ant_rule_old_not_null>) then
    update `ad_<rule_id>_sa` set support=support-1 where <ant_old_match>; 
    if (<con_rule_old_not_null>) then 
     update `ad_<rule_id>_sac` set support=support-1 where <ant_old_match> and <con_old_match>;
    end if; 
   end if; 

   if (<ant_rule_new_not_null>) then
    update `ad_<rule_id>_sa` set support=support+1 where <ant_new_match>;
    if (ROW_COUNT() = 0) then
     insert into `ad_<rule_id>_sa` values (<ant_new_list>,1); 
    end if; 
    if (<con_rule_new_not_null>) then
     update `ad_<rule_id>_sac` set support=support+1 where <ant_new_match> and <con_new_match>;
     if (ROW_COUNT() = 0) then
      insert into `ad_<rule_id>_sac` values (<ant_new_list>,<con_new_list>,1); 
     end if; 
    end if; 
   end if; 

  end if; 
   
 else 
  if (<consequent_change_UPDATE>) then
   if (<con_rule_old_not_null>) then
    update `ad_<rule_id>_sc` set support=support-1 where <con_old_match>; 
    if (<ant_rule_old_not_null>) then 
     update `ad_<rule_id>_sac` set support=support-1 where <ant_old_match> and <con_old_match>;
    end if; 
   end if; 

   if (<con_rule_new_not_null>) then
    update `ad_<rule_id>_sc` set support=support+1 where <con_new_match>; 
    if (ROW_COUNT() = 0) then
     insert into `ad_<rule_id>_sc` values (<con_new_list>,1); 
    end if; 
    if (<ant_rule_new_not_null>) then
     update `ad_<rule_id>_sac` set support=support+1 where <ant_new_match> and <con_new_match>;
     if (ROW_COUNT() = 0) then
     insert into `ad_<rule_id>_sac` values (<ant_new_list>,<con_new_list>,1); 
     end if; 
    end if;   
   end if; 
  end if;
 end if;

END;


DROP TRIGGER IF EXISTS `deleting_<rule_id>`;
CREATE TRIGGER `deleting_<rule_id>` AFTER DELETE ON `<table_name>` FOR EACH ROW 

BEGIN

 update `count_row_<table_name>` set count=count-1;

 if (<ant_rule_old_not_null>) then
  update `ad_<rule_id>_sa` set support=support-1 where <ant_old_match>; 
  if (<con_rule_old_not_null>) then
   update `ad_<rule_id>_sac` set support=support-1 where <ant_old_match> and <con_old_match>;
   update `ad_<rule_id>_sc` set support=support-1 where <con_old_match>; 
  end if; 
 elseif (<con_rule_old_not_null>) then
  update `ad_<rule_id>_sc` set support=support-1 where <con_old_match>;
 end if; 
END;


CREATE TRIGGER `inserting_<rule_id>` AFTER INSERT ON `<table_name>` FOR EACH ROW 

BEGIN

 update `count_row_<table_name>` set count=count+1;

 if (<ant_rule_new_not_null>) then
  update `ad_<rule_id>_sa` set support=support+1 where <ant_new_match>; 
  if (ROW_COUNT() = 0) then
   insert into `ad_<rule_id>_sa` values (<ant_new_list>,1); 
  end if; 
  if (<con_rule_new_not_null>) then
   update `ad_<rule_id>_sac` set support=support+1 where <ant_new_match> and <con_new_match>;
   if (ROW_COUNT() = 0) then
    insert into `ad_<rule_id>_sac` values (<ant_new_list>,<con_new_list>,1); 
   end if;
   update `ad_<rule_id>_sc` set support=support+1 where <con_new_match>;
   if (ROW_COUNT() = 0) then
    insert into `ad_<rule_id>_sc` values (<con_new_list>,1); 
   end if; 
  end if; 
 elseif (<con_rule_new_not_null>) then 
  update `ad_<rule_id>_sc` set support=support+1 where <con_new_match>;
  if (ROW_COUNT() = 0) then
   insert into `ad_<rule_id>_sc` values (<con_new_match>,1); 
  end if; 
 end if;
END;


DROP PROCEDURE IF EXISTS `measures_update_<rule_id>`;
CREATE PROCEDURE `measures_update_<rule_id>`()

BEGIN

 SELECT POWER(count,2) FROM `count_row_<table_name>` limit 1 into @cou;
  
 select sum(power(support,2)) into @sal from `ad_<rule_id>_sa`;
 select sum(power(support,2)) into @scl from `ad_<rule_id>_sc`;
 select sum(power(support,2)) into @srl from `ad_<rule_id>_sac`;

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


DROP VIEW IF EXISTS `rules_certainty_factor`;
CREATE VIEW `rules_certainty_factor` AS 
select `rule_id`,`antecedent`,`consequent`,`cf` 
 from `data_rules` 
 order by `rule_id`;