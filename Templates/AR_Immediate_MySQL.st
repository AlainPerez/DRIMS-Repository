DROP TRIGGER IF EXISTS `updating_<rule_id>`;
CREATE TRIGGER `updating_<rule_id>` AFTER UPDATE ON `<table_name>` FOR EACH ROW 

BEGIN

 if (<antecedent_change_UPDATE>) then 
  if (<consequent_change_UPDATE>) then
   set @boolantold = (<ant_rule_old_expr>) and (<ant_rule_old_not_null>);
   set @boolantnew = (<ant_rule_new_expr>) and (<ant_rule_new_not_null>);
   set @boolconnew = (<con_rule_new_expr>) and (<con_rule_new_not_null>); 
   set @boolconold = (<con_rule_old_expr>) and (<con_rule_old_not_null>);
   if (not(@boolantold) and @boolantnew) then update data_rules set sa = sa+1 where rule_id='<rule_id>';
   else
    if (@boolantold and not(@boolantnew)) then update data_rules set sa = sa-1 where rule_id='<rule_id>';
    end if;
   end if; 
   if (not(@boolconold) and @boolconnew) then update data_rules set sc = sc+1 where rule_id='<rule_id>';
   else
    if (@boolconold and not(@boolconnew)) then update data_rules set sc = sc-1 where rule_id='<rule_id>';
    end if;
   end if; 
   if (not(@boolantold and @boolconold) and (@boolantnew and @boolconnew)) then update data_rules set sac = sac+1 where rule_id='<rule_id>';
   else 
    if ((@boolantold and @boolconold) and not(@boolantnew and @boolconnew)) then update data_rules set sac = sac-1 where rule_id='<rule_id>';
    end if;
   end if; 

  else 
   set @boolantold = (<ant_rule_old_expr>) and (<ant_rule_old_not_null>);
   set @boolantnew = (<ant_rule_new_expr>) and (<ant_rule_new_not_null>);
   set @boolconnew = (<con_rule_new_expr>) and (<con_rule_new_not_null>); 
   if (not(@boolantold) and @boolantnew) then 
    if (@boolconnew) then update data_rules set sac = sac+1, sa = sa+1 where rule_id='<rule_id>'; 
    else update data_rules set sa = sa+1 where rule_id='<rule_id>';
    end if;
   else 
    if (@boolantold and not(@boolantnew)) then 
     if (@boolconnew) then update data_rules set sac = sac-1, sa = sa-1 where rule_id='<rule_id>'; 
     else update data_rules set sa = sa-1 where rule_id='<rule_id>';
     end if; 
    end if; 
   end if; 
  end if; 
 else 
  if (<consequent_change_UPDATE>) then
   set @boolconnew = (<con_rule_new_expr>) and (<con_rule_new_not_null>); 
   set @boolconold = (<con_rule_old_expr>) and (<con_rule_old_not_null>);
   set @boolantnew = (<ant_rule_new_expr>) and (<ant_rule_new_not_null>);
   if (not(@boolconold) and @boolconnew) then 
    if (@boolantnew) then update data_rules set sac = sac+1, sc = sc+1 where rule_id='<rule_id>'; 
    else update data_rules set sc = sc+1 where rule_id='<rule_id>';
    end if;
   else 
    if (@boolconold and not(@boolconnew)) then 
     if (@boolantnew) then update data_rules set sac = sac-1, sc = sc-1 where rule_id='<rule_id>'; 
     else update data_rules set sc = sc-1 where rule_id='<rule_id>';
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
END;

DROP TRIGGER IF EXISTS `inserting_<rule_id>`;
CREATE TRIGGER `inserting_<rule_id>` AFTER INSERT ON `<table_name>` FOR EACH ROW 

BEGIN

 update `count_row_<table_name>` set count=count+1;

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
END;


DROP PROCEDURE IF EXISTS `measures_update_<rule_id>`;
CREATE PROCEDURE `measures_update_<rule_id>`()

BEGIN

 SELECT count into @cou FROM `count_row_<table_name>` limit 1;
  
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
INSERT INTO data_rules values ('<rule_id>','<ant_rule_expr_xx>','<con_rule_expr_xx>',(select count(*) from `<table_name>` where <ant_rule_expr>),(select count(*) from `<table_name>` where <con_rule_expr>),(select count(*) from `<table_name>` where <ant_rule_expr> and <con_rule_expr>),0);


DROP VIEW IF EXISTS `rules_certainty_factor`;
CREATE VIEW `rules_certainty_factor` AS 
select `rule_id`,`antecedent`,`consequent`,`cf` 
 from `data_rules` 
 order by `rule_id`;