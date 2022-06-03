CREATE TABLE `organisation`.`departmentdetails` (
  `departmentID` VARCHAR(45) NOT NULL,
  `DepartmentName` VARCHAR(45) NOT NULL,
  `Salary` INT NOT NULL,
  PRIMARY KEY (`departmentID`));
INSERT INTO `organisation`.`departmentdetails` (`departmentID`, `DepartmentName`, `Salary`) VALUES ('A1', 'Administration', '20000');
  INSERT INTO `organisation`.`departmentdetails` (`departmentID`, `DepartmentName`, `Salary`) VALUES ('E1', 'Engineering', '40000');
  INSERT INTO `organisation`.`departmentdetails` (`departmentID`, `DepartmentName`, `Salary`) VALUES ('H1', 'Human Resource', '30000');
  INSERT INTO `organisation`.`departmentdetails` (`departmentID`, `DepartmentName`, `Salary`) VALUES ('S1', 'Sales', '30000'); 
  CREATE TABLE `organisation`.`employee details` (
  `employeeId` INT NOT NULL,
  `employeeName` VARCHAR(45) NOT NULL,
  `employeeAge` VARCHAR(45) NOT NULL,
  `employeePass` VARCHAR(45) NOT NULL,
  `departmentID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`employeeId`),
  FOREIGN KEY(departmentID) REFERENCES departmentdetails(departmentID)
);
INSERT INTO `organisation`.`employee details` (`employeeId`, `employeeName`, `employeeAge`, `employeePass`, `departmentID`) VALUES ('111', 'Shruthi', '21', '12345', 'A1');
INSERT INTO `organisation`.`employee details` (`employeeId`, `employeeName`, `employeeAge`, `employeePass`, `departmentID`) VALUES ('112', 'Arun', '22', '12345', 'E1');
INSERT INTO `organisation`.`employee details` (`employeeId`, `employeeName`, `employeeAge`, `employeePass`, `departmentID`) VALUES ('113', 'Kavya', '23', '12345', 'H1');
 INSERT INTO `organisation`.`employee details` (`employeeId`, `employeeName`, `employeeAge`, `employeePass`, `departmentID`) VALUES ('114', 'Shine', '24', '12345', 'E1');
  INSERT INTO `organisation`.`employee details` (`employeeId`, `employeeName`, `employeeAge`, `employeePass`, `departmentID`) VALUES ('115', 'Vijay', '25', '12345', 'S1');