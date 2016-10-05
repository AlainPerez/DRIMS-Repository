CREATE OR REPLACE FUNCTION "updating_ID_1426845038480"() RETURNS trigger
 LANGUAGE plpgsql
 AS $$
declare 

 boolantnew boolean;
 boolantold boolean;
 boolconnew boolean;
 boolconold boolean;

BEGIN

 if ((old."Sex" <> new."Sex") or (old."Sex" is null and new."Sex" is not null) or (old."Sex" is not null and new."Sex" is null) OR (old."pc_pcontestadas" <> new."pc_pcontestadas") or (old."pc_pcontestadas" is null and new."pc_pcontestadas" is not null) or (old."pc_pcontestadas" is not null and new."pc_pcontestadas" is null)) then 
  if ((old."avg_score" <> new."avg_score") or (old."avg_score" is null and new."avg_score" is not null) or (old."avg_score" is not null and new."avg_score" is null)) then

    boolantold := (old."Sex" = 'f' OR old."pc_pcontestadas" BETWEEN 300 AND 400) and ((old."Sex" is not null) AND (old."pc_pcontestadas" is not null));
    boolantnew := (new."Sex" = 'f' OR new."pc_pcontestadas" BETWEEN 300 AND 400) and ((new."Sex" is not null) AND (new."pc_pcontestadas" is not null));
    boolconnew := (new."avg_score" = 4.5) and ((new."avg_score" is not null)); 
    boolconold := (old."avg_score" = 4.5) and ((old."avg_score" is not null));

   if (not(boolantold) and boolantnew) then update data_rules set sa = sa+1 where rule_id='ID_1426845038480'; 
   else
     if (boolantold and not(boolantnew)) then update data_rules set sa = sa-1 where rule_id='ID_1426845038480'; 
     end if;
   end if; 
   if (not(boolconold) and boolconnew) then update data_rules set sc = sc+1 where rule_id='ID_1426845038480'; 
   else
     if (boolconold and not(boolconnew)) then update data_rules set sc = sc-1 where rule_id='ID_1426845038480'; 
     end if;
   end if; 
   if (not(boolantold and boolconold) and (boolantnew and boolconnew)) then update data_rules set sac = sac+1 where rule_id='ID_1426845038480'; 
   else 
     if ((boolantold and boolconold) and not(boolantnew and boolconnew)) then update data_rules set sac = sac-1 where rule_id='ID_1426845038480'; 
     end if;
   end if; 
   else 

    boolantold := (old."Sex" = 'f' OR old."pc_pcontestadas" BETWEEN 300 AND 400) and ((old."Sex" is not null) AND (old."pc_pcontestadas" is not null));
    boolantnew := (new."Sex" = 'f' OR new."pc_pcontestadas" BETWEEN 300 AND 400) and ((new."Sex" is not null) AND (new."pc_pcontestadas" is not null));
    boolconnew := (new."avg_score" = 4.5) and ((new."avg_score" is not null));

    if (not(boolantold) and boolantnew) then 
     if (boolconnew) then update data_rules set sac = sac+1, sa = sa+1 where rule_id='ID_1426845038480'; 
     else update data_rules set sa = sa+1 where rule_id='ID_1426845038480';
     end if;
    else 
     if (boolantold and not(boolantnew)) then 
      if (boolconnew) then update data_rules set sac = sac-1, sa = sa-1 where rule_id='ID_1426845038480'; 
      else update data_rules set sa = sa-1 where rule_id='ID_1426845038480';
      end if; 
     end if; 
    end if; 
   end if; 
 else 
  if ((old."avg_score" <> new."avg_score") or (old."avg_score" is null and new."avg_score" is not null) or (old."avg_score" is not null and new."avg_score" is null)) then
   boolconnew := (new."avg_score" = 4.5) and ((new."avg_score" is not null)); 
   boolconold := (old."avg_score" = 4.5) and ((old."avg_score" is not null));
   boolantnew := (new."Sex" = 'f' OR new."pc_pcontestadas" BETWEEN 300 AND 400) and ((new."Sex" is not null) AND (new."pc_pcontestadas" is not null));

   if (not(boolconold) and boolconnew) then 
    if (boolantnew) then update data_rules set sac = sac+1, sc = sc+1 where rule_id='ID_1426845038480'; 
    else update data_rules set sc = sc+1 where rule_id='ID_1426845038480';
    end if;
   else 
    if (boolconold and not(boolconnew)) then
     if (boolantnew) then update data_rules set sac = sac-1, sc = sc-1 where rule_id='ID_1426845038480'; 
     else update data_rules set sc = sc-1 where rule_id='ID_1426845038480';
     end if; 
    end if; 
   end if;
  end if;
 end if;

 return null;
END $$;


CREATE OR REPLACE FUNCTION "deleting_ID_1426845038480"() RETURNS trigger
 LANGUAGE plpgsql
 AS $$
BEGIN

 perform setval('"count_row_swad_DB"', nextval('"count_row_swad_DB"')-2);

 if (old."Sex" = 'f' OR old."pc_pcontestadas" BETWEEN 300 AND 400) then 
  if (old."avg_score" = 4.5) then 
   update data_rules set sac = sac-1, sa = sa-1, sc = sc - 1 where rule_id='ID_1426845038480'; 
  else 
   update data_rules set sa = sa-1 where rule_id='ID_1426845038480';
  end if;
  else if (old."avg_score" = 4.5) then
   update data_rules set sc = sc-1 where rule_id='ID_1426845038480'; 
  end if;
 end if;

 return null;
END$$;


CREATE OR REPLACE FUNCTION "inserting_ID_1426845038480"() RETURNS trigger
 LANGUAGE plpgsql
 AS $$ 
BEGIN

 perform nextval('"count_row_swad_DB"');

 if (new."Sex" = 'f' OR new."pc_pcontestadas" BETWEEN 300 AND 400) then 
  if (new."avg_score" = 4.5) then 
   update data_rules set sac = sac+1, sa = sa+1, sc = sc+1 where rule_id='ID_1426845038480'; 
  else 
   update data_rules set sa = sa+1 where rule_id='ID_1426845038480';
  end if;
 else if (new."avg_score" = 4.5) then   
  update data_rules set sc = sc+1 where rule_id='ID_1426845038480'; 
  end if;
 end if;

 return null;
END$$;


CREATE OR REPLACE FUNCTION "measures_update_ID_1426845038480"() RETURNS void
 LANGUAGE plpgsql
 AS $$
declare 

 nva bigint;
 nvc bigint;
 nvr bigint;
 cou bigint;


BEGIN

 select into cou setval('"count_row_swad_DB"', nextval('"count_row_swad_DB"')-1);

 select into nva,nvc,nvr sa,sc,sac from data_rules where rule_id='ID_1426845038480';

  if (nva <> 0 and cou <> 0) then 
  if (nvr::double precision/nva::double precision > nvc::double precision/cou::double precision) then
	if ((1::double precision - nvc::double precision/cou::double precision) <> 0) then
	  update data_rules set cf = (nvr::double precision / nva::double precision - nvc::double precision/cou::double precision) / (1::double precision - nvc::double precision/cou::double precision) where rule_id='ID_1426845038480';
	end if;
  else if (nvc <> 0) then
	     update data_rules set cf = (nvr::double precision / nva::double precision - nvc::double precision/cou::double precision) / (nvc::double precision/cou::double precision) where rule_id='ID_1426845038480'; 
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


DELETE FROM data_rules WHERE rule_id = 'ID_1426845038480';
INSERT INTO data_rules values ('ID_1426845038480','Sex = f OR pc_pcontestadas BETWEEN 300 AND 400','avg_score = 4.5',(select count(*) from "swad_DB" where "Sex" = 'f' OR "pc_pcontestadas" BETWEEN 300 AND 400),(select count(*) from "swad_DB" where "avg_score" = 4.5),(select count(*) from "swad_DB" where "Sex" = 'f' OR "pc_pcontestadas" BETWEEN 300 AND 400 and "avg_score" = 4.5),0);


CREATE OR REPLACE VIEW rules_certainty_factor AS
 SELECT rule_id, antecedent, consequent, cf as certainty_factor
 FROM data_rules
 ORDER BY rule_id;


DROP SEQUENCE IF EXISTS "count_row_swad_DB";
CREATE SEQUENCE "count_row_swad_DB"
 START WITH 0
 INCREMENT BY 1
 MINVALUE 0
 NO MAXVALUE
 CACHE 1;
SELECT setval('"count_row_swad_DB"', (select count(*) from "swad_DB"));


DROP TRIGGER IF EXISTS "tdelete_ID_1426845038480" ON "swad_DB";
DROP TRIGGER IF EXISTS "tinsert_ID_1426845038480" ON "swad_DB";
DROP TRIGGER IF EXISTS "tupdate_ID_1426845038480" ON "swad_DB";


CREATE TRIGGER "tdelete_ID_1426845038480" AFTER DELETE ON "swad_DB" FOR EACH ROW EXECUTE PROCEDURE "deleting_ID_1426845038480"();
CREATE TRIGGER "tinsert_ID_1426845038480" AFTER INSERT ON "swad_DB" FOR EACH ROW EXECUTE PROCEDURE "inserting_ID_1426845038480"();
CREATE TRIGGER "tupdate_ID_1426845038480" AFTER UPDATE ON "swad_DB" FOR EACH ROW EXECUTE PROCEDURE "updating_ID_1426845038480"();