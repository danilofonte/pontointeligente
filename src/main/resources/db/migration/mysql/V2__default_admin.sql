INSERT INTO `company` (`id`, `cnpj`, `modified_time`, `creation_time`, `company_name`) 
VALUES (NULL, '82198127000121', CURRENT_DATE(), CURRENT_DATE(), 'Default IT');

INSERT INTO `employee` (`id`, `cpf`, `modified_time`, `creation_time`, `email`, `name`, 
`role`, `lunch_hour_qty`, `working_hours_qty`, `password`, `hour_value`, `company_id`) 
VALUES (NULL, '16248890935', CURRENT_DATE(), CURRENT_DATE(), 'admin@admin.com', 'ADMIN', 'ROLE_ADMIN', NULL, NULL, 
'$2a$06$xIvBeNRfS65L1N17I7JzgefzxEuLAL0Xk0wFAgIkoNqu9WD6rmp4m', NULL, 
(SELECT `id` FROM `company` WHERE `cnpj` = '82198127000121'));