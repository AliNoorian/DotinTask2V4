ALTER TABLE t_category
     ADD `c_category_name` ENUM('EMPLOYEE_ROLE', 'LEAVE_STATUS');

ALTER TABLE t_category_element
     ADD `c_leave_status` ENUM('APPROVED', 'REJECTED');

ALTER TABLE t_category_element
     ADD `c_employee_role` ENUM('PROGRAMMER', 'TESTER',
      'OTHER', 'PROGRAMMER_MANGER', 'TESTER_MANAGER', 'OTHER_MANAGER');