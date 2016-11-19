DROP TRIGGER IF EXISTS `updating_ID_1479511194626`;
CREATE TRIGGER `updating_ID_1479511194626` AFTER UPDATE ON `color_moments` FOR EACH ROW 

BEGIN

 if ((old.`hskew` <> new.`hskew`) or (old.`hskew` is null and new.`hskew` is not null) or (old.`hskew` is not null and new.`hskew` is null)) then 
  if ((old.`hstdv` <> new.`hstdv`) or (old.`hstdv` is null and new.`hstdv` is not null) or (old.`hstdv` is not null and new.`hstdv` is null)) then
   set @boolantold = (old.`hskew` BETWEEN 0.8 AND 1.4) and ((old.`hskew` is not null));
   set @boolantnew = (new.`hskew` BETWEEN 0.8 AND 1.4) and ((new.`hskew` is not null));
   set @boolconnew = (new.`hstdv` BETWEEN 1.37 AND 2.93) and ((new.`hstdv` is not null)); 
   set @boolconold = (old.`hstdv` BETWEEN 1.37 AND 2.93) and ((old.`hstdv` is not null));
   if (not(@boolantold) and @boolantnew) then update data_rules set sa = sa+1 where rule_id='ID_1479511194626';
   else
    if (@boolantold and not(@boolantnew)) then update data_rules set sa = sa-1 where rule_id='ID_1479511194626';
    end if;
   end if; 
   if (not(@boolconold) and @boolconnew) then update data_rules set sc = sc+1 where rule_id='ID_1479511194626';
   else
    if (@boolconold and not(@boolconnew)) then update data_rules set sc = sc-1 where rule_id='ID_1479511194626';
    end if;
   end if; 
   if (not(@boolantold and @boolconold) and (@boolantnew and @boolconnew)) then update data_rules set sac = sac+1 where rule_id='ID_1479511194626';
   else 
    if ((@boolantold and @boolconold) and not(@boolantnew and @boolconnew)) then update data_rules set sac = sac-1 where rule_id='ID_1479511194626';
    end if;
   end if; 

  else 
   set @boolantold = (old.`hskew` BETWEEN 0.8 AND 1.4) and ((old.`hskew` is not null));
   set @boolantnew = (new.`hskew` BETWEEN 0.8 AND 1.4) and ((new.`hskew` is not null));
   set @boolconnew = (new.`hstdv` BETWEEN 1.37 AND 2.93) and ((new.`hstdv` is not null)); 
   if (not(@boolantold) and @boolantnew) then 
    if (@boolconnew) then update data_rules set sac = sac+1, sa = sa+1 where rule_id='ID_1479511194626'; 
    else update data_rules set sa = sa+1 where rule_id='ID_1479511194626';
    end if;
   else 
    if (@boolantold and not(@boolantnew)) then 
     if (@boolconnew) then update data_rules set sac = sac-1, sa = sa-1 where rule_id='ID_1479511194626'; 
     else update data_rules set sa = sa-1 where rule_id='ID_1479511194626';
     end if; 
    end if; 
   end if; 
  end if; 
 else 
  if ((old.`hstdv` <> new.`hstdv`) or (old.`hstdv` is null and new.`hstdv` is not null) or (old.`hstdv` is not null and new.`hstdv` is null)) then
   set @boolconnew = (new.`hstdv` BETWEEN 1.37 AND 2.93) and ((new.`hstdv` is not null)); 
   set @boolconold = (old.`hstdv` BETWEEN 1.37 AND 2.93) and ((old.`hstdv` is not null));
   set @boolantnew = (new.`hskew` BETWEEN 0.8 AND 1.4) and ((new.`hskew` is not null));
   if (not(@boolconold) and @boolconnew) then 
    if (@boolantnew) then update data_rules set sac = sac+1, sc = sc+1 where rule_id='ID_1479511194626'; 
    else update data_rules set sc = sc+1 where rule_id='ID_1479511194626';
    end if;
   else 
    if (@boolconold and not(@boolconnew)) then 
     if (@boolantnew) then update data_rules set sac = sac-1, sc = sc-1 where rule_id='ID_1479511194626'; 
     else update data_rules set sc = sc-1 where rule_id='ID_1479511194626';
     end if; 
    end if; 
   end if;
  end if;
 end if;
END;


DROP TRIGGER IF EXISTS `deleting_ID_1479511194626`;
CREATE TRIGGER `deleting_ID_1479511194626` AFTER DELETE ON `color_moments` FOR EACH ROW

BEGIN

 update `count_row_color_moments` set count=count-1;

 if (old.`hskew` BETWEEN 0.8 AND 1.4) then 
  if (old.`hstdv` BETWEEN 1.37 AND 2.93) then 
   update data_rules set sac = sac-1, sa = sa-1, sc = sc - 1 where rule_id='ID_1479511194626'; 
  else 
   update data_rules set sa = sa-1 where rule_id='ID_1479511194626';
  end if;
  else if (old.`hstdv` BETWEEN 1.37 AND 2.93) then
   update data_rules set sc = sc-1 where rule_id='ID_1479511194626'; 
  end if;
 end if;
END;

DROP TRIGGER IF EXISTS `inserting_ID_1479511194626`;
CREATE TRIGGER `inserting_ID_1479511194626` AFTER INSERT ON `color_moments` FOR EACH ROW 

BEGIN

 update `count_row_color_moments` set count=count+1;

 if (new.`hskew` BETWEEN 0.8 AND 1.4) then 
  if (new.`hstdv` BETWEEN 1.37 AND 2.93) then 
   update data_rules set sac = sac+1, sa = sa+1, sc = sc+1 where rule_id='ID_1479511194626'; 
  else 
   update data_rules set sa = sa+1 where rule_id='ID_1479511194626';
  end if;
  else if (new.`hstdv` BETWEEN 1.37 AND 2.93) then   
   update data_rules set sc = sc+1 where rule_id='ID_1479511194626'; 
  end if;
 end if;
END;


DROP PROCEDURE IF EXISTS `measures_update_ID_1479511194626`;
CREATE PROCEDURE `measures_update_ID_1479511194626`()

BEGIN

 SELECT count into @cou FROM `count_row_color_moments` limit 1;

 select sa,sc,sac into @nva,@nvc,@nvr from data_rules where rule_id='ID_1479511194626';

 if (@nva <> 0 and @cou <> 0) then  
  if (@nvr/@nva > @nvc/@cou) then
   if (1 - @nvc/@cou <> 0) then
     update data_rules set cf = (@nvr / @nva - @nvc/@cou) / (1 - @nvc/@cou) where rule_id='ID_1479511194626';
   end if;
  else
   if (@nvc/@cou <> 0) then
     update data_rules set cf = (@nvr / @nva - @nvc/@cou) / (@nvc/@cou) where rule_id='ID_1479511194626'; 
   end if; 
  end if;
 end if;
END;


DROP TABLE IF EXISTS `count_row_color_moments`;
CREATE TABLE `count_row_color_moments` (
 `count` bigint NOT NULL,
 PRIMARY KEY (`count`)
);
INSERT INTO `count_row_color_moments` values ((select count(*) from `color_moments`));


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


DELETE FROM data_rules WHERE rule_id = 'ID_1479511194626';
INSERT INTO data_rules values ('ID_1479511194626','hskew BETWEEN 0.8 AND 1.4','hstdv BETWEEN 1.37 AND 2.93',(select count(*) from `color_moments` where `hskew` BETWEEN 0.8 AND 1.4),(select count(*) from `color_moments` where `hstdv` BETWEEN 1.37 AND 2.93),(select count(*) from `color_moments` where `hskew` BETWEEN 0.8 AND 1.4 and `hstdv` BETWEEN 1.37 AND 2.93),0);


DROP VIEW IF EXISTS `rules_certainty_factor`;
CREATE VIEW `rules_certainty_factor` AS 
select `rule_id`,`antecedent`,`consequent`,`cf` 
 from `data_rules` 
 order by `rule_id`;