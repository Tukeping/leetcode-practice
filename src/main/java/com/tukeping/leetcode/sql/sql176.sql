--
-- @lc app=leetcode.cn id=176 lang=mysql
--
-- [176] 第二高的薪水
--
-- https://leetcode-cn.com/problems/second-highest-salary/description/
--
-- database
-- Easy (33.39%)
-- Likes:    506
-- Dislikes: 0
-- Total Accepted:    73.2K
-- Total Submissions: 215.8K
-- Testcase Example:  '{"headers": {"Employee": ["Id", "Salary"]}, "rows": {"Employee": [[1, 100], [2, 200], [3, 300]]}}'
--
-- 编写一个 SQL 查询，获取 Employee 表中第二高的薪水（Salary） 。
--
-- +----+--------+
-- | Id | Salary |
-- +----+--------+
-- | 1  | 100    |
-- | 2  | 200    |
-- | 3  | 300    |
-- +----+--------+
--
--
-- 例如上述 Employee 表，SQL查询应该返回 200 作为第二高的薪水。如果不存在第二高的薪水，那么查询应返回 null。
--
-- +---------------------+
-- | SecondHighestSalary |
-- +---------------------+
-- | 200                 |
-- +---------------------+
--
--
--

-- @lc code=start

Create table If Not Exists Employee (Id int, Salary int);

# test case 1
Truncate table Employee;
insert into Employee (Id, Salary) values ('1', '100');
insert into Employee (Id, Salary) values ('2', '200');
insert into Employee (Id, Salary) values ('3', '300');

# test case 2
Truncate table Employee;
insert into Employee (Id, Salary) values ('1', '100');

select * from Employee;

# Write your MySQL query statement below

select IFNULL((select DISTINCT Salary from Employee order by Salary desc limit 1,1), NULL)
           as SecondHighestSalary;

-- @lc code=end