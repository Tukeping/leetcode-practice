insert into Person(Email) values('john@example.com');
insert into Person(Email) values('bob@example.com');
insert into Person(Email) values('john@example.com');

select Id, Email from Person;

SELECT p1.*
FROM Person p1,
     Person p2
WHERE p1.Email = p2.Email;

SELECT p1.*
FROM Person p1,
     Person p2
WHERE p1.Email = p2.Email AND p1.Id > p2.Id;

DELETE p1 FROM Person p1,
               Person p2
WHERE p1.Email = p2.Email AND p1.Id > p2.Id;

select p.Id
from (select Email,MIN(Id) Id from Person group by Email having count(Email) > 1) as s
left join Person p on p.Email = s.Email
where p.Id > s.Id;

delete from Person
where Email in
(select t.Email from (select Email, count(1) as Count from Person group by Email) as t where t.Count > 1)
and Id not in
(select t.Id from (select MIN(Id) as Id, count(1) as Count from Person group by Email) as t where t.Count > 1);