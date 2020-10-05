-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 31, 2020 at 11:43 AM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `crm_new`
--

-- --------------------------------------------------------

--
-- Table structure for table `client_store`
--

CREATE TABLE `client_store` (
  `cl_id` int(11) NOT NULL,
  `cl_address` varchar(255) DEFAULT NULL,
  `cl_bcycle` date DEFAULT NULL,
  `cl_city` varchar(255) DEFAULT NULL,
  `cl_country` varchar(255) DEFAULT NULL,
  `cl_email` varchar(255) DEFAULT NULL,
  `cl_join_date` date DEFAULT NULL,
  `cl_name` varchar(255) DEFAULT NULL,
  `cl_owner` varchar(255) DEFAULT NULL,
  `cl_phone` varchar(255) DEFAULT NULL,
  `cl_type` int(11) DEFAULT NULL,
  `cl_website` varchar(255) DEFAULT NULL,
  `create_don` date DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `from_lead` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client_store`
--

INSERT INTO `client_store` (`cl_id`, `cl_address`, `cl_bcycle`, `cl_city`, `cl_country`, `cl_email`, `cl_join_date`, `cl_name`, `cl_owner`, `cl_phone`, `cl_type`, `cl_website`, `create_don`, `created_by`, `from_lead`) VALUES
(3, 'Plot# 29/3 block 3, road # 2, Bahaduryar jang Society, Bahadurabad', NULL, 'Karachi', 'Pakistan', 'info@noor.com.pk', NULL, 'Noor Distributor Karachi', 'Shehzad Noor', '34135811', 1, 'www.noor.com.pk', NULL, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `client_type`
--

CREATE TABLE `client_type` (
  `ct_code` int(11) NOT NULL,
  `ct_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client_type`
--

INSERT INTO `client_type` (`ct_code`, `ct_name`) VALUES
(10, 'active');

-- --------------------------------------------------------

--
-- Table structure for table `contact_store`
--

CREATE TABLE `contact_store` (
  `cs_id` int(11) NOT NULL,
  `cl_id` int(11) DEFAULT NULL,
  `created_done` date DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `cs_city` varchar(255) DEFAULT NULL,
  `cs_country` varchar(255) DEFAULT NULL,
  `cs_dob` date DEFAULT NULL,
  `cs_first_name` varchar(255) DEFAULT NULL,
  `cs_last_name` varchar(255) DEFAULT NULL,
  `cs_note` varchar(255) DEFAULT NULL,
  `freeze` int(11) DEFAULT NULL,
  `cs_address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contact_store`
--

INSERT INTO `contact_store` (`cs_id`, `cl_id`, `created_done`, `created_by`, `cs_city`, `cs_country`, `cs_dob`, `cs_first_name`, `cs_last_name`, `cs_note`, `freeze`, `cs_address`) VALUES
(11, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `domain_list`
--

CREATE TABLE `domain_list` (
  `domain_code` int(11) NOT NULL,
  `domain_wb` int(11) DEFAULT NULL,
  `domain_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `domain_list`
--

INSERT INTO `domain_list` (`domain_code`, `domain_wb`, `domain_name`) VALUES
(12, 1, 'noor.com.pk');

-- --------------------------------------------------------

--
-- Table structure for table `email_general`
--

CREATE TABLE `email_general` (
  `email_no` int(11) NOT NULL,
  `attach` varchar(255) DEFAULT NULL,
  `bcc_address` varchar(255) DEFAULT NULL,
  `cc_address` varchar(255) DEFAULT NULL,
  `email_body` varchar(255) DEFAULT NULL,
  `freeze` int(11) DEFAULT NULL,
  `from_address` varchar(255) DEFAULT NULL,
  `message_no` int(11) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `times_tamp` date DEFAULT NULL,
  `to_address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `email_general`
--

INSERT INTO `email_general` (`email_no`, `attach`, `bcc_address`, `cc_address`, `email_body`, `freeze`, `from_address`, `message_no`, `subject`, `times_tamp`, `to_address`) VALUES
(9, '', '', '', 'kuch naya kar jao', 0, 'sales@burhanisolutions.com.pk', 2, 'with api', NULL, 'isko20951@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `email_list`
--

CREATE TABLE `email_list` (
  `email_id` int(11) NOT NULL,
  `cl_id` int(11) DEFAULT NULL,
  `cs_id` int(11) DEFAULT NULL,
  `email_name` varchar(255) DEFAULT NULL,
  `ls_id` int(11) DEFAULT NULL,
  `user_code` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `email_list`
--

INSERT INTO `email_list` (`email_id`, `cl_id`, `cs_id`, `email_name`, `ls_id`, `user_code`) VALUES
(13, 0, 0, NULL, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `email_relation`
--

CREATE TABLE `email_relation` (
  `email_no` int(11) NOT NULL,
  `cl_id` int(11) DEFAULT NULL,
  `cs_id` int(11) DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  `email_type` int(11) DEFAULT NULL,
  `user_code` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `email_relation`
--

INSERT INTO `email_relation` (`email_no`, `cl_id`, `cs_id`, `email_id`, `email_type`, `user_code`) VALUES
(14, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `email_sent`
--

CREATE TABLE `email_sent` (
  `email_no` int(11) NOT NULL,
  `attach` varchar(255) DEFAULT NULL,
  `bcc_address` varchar(255) DEFAULT NULL,
  `cc_address` varchar(255) DEFAULT NULL,
  `email_body` varchar(255) DEFAULT NULL,
  `esno` int(11) DEFAULT NULL,
  `freze` int(11) DEFAULT NULL,
  `from_address` varchar(255) DEFAULT NULL,
  `sent` int(11) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `times_tamp` date DEFAULT NULL,
  `to_address` varchar(255) DEFAULT NULL,
  `upload_attach` char(1) DEFAULT NULL,
  `user_code` int(11) DEFAULT NULL,
  `freeze` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `email_sent`
--

INSERT INTO `email_sent` (`email_no`, `attach`, `bcc_address`, `cc_address`, `email_body`, `esno`, `freze`, `from_address`, `sent`, `subject`, `times_tamp`, `to_address`, `upload_attach`, `user_code`, `freeze`) VALUES
(8, '', 'sales@burhanisolutions.com.pk', '', 'kuch naya kar jao', 0, 0, 'sales@burhanisolutions.com.pk', 1, 'with api', '1969-12-31', 'isko20951@gmail.com', '\0', 1, 0),
(15, '', 'sales@burhanisolutions.com.pk', '', 'kuch naya kar jao', 0, NULL, 'sales@burhanisolutions.com.pk', 1, 'with api', '1969-12-31', 'isko20951@gmail.com', '\0', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `email_store`
--

CREATE TABLE `email_store` (
  `emno` int(11) NOT NULL,
  `attch` varchar(255) DEFAULT NULL,
  `ccadd` varchar(255) DEFAULT NULL,
  `ebody` varchar(255) DEFAULT NULL,
  `lockd` int(11) DEFAULT NULL,
  `esolv` char(1) DEFAULT NULL,
  `solvby` int(11) DEFAULT NULL,
  `freze` int(11) DEFAULT NULL,
  `fradd` varchar(255) DEFAULT NULL,
  `locktime` date DEFAULT NULL,
  `manual` int(11) DEFAULT NULL,
  `msgno` int(11) DEFAULT NULL,
  `solvtime` date DEFAULT NULL,
  `sbjct` varchar(255) DEFAULT NULL,
  `tstmp` date DEFAULT NULL,
  `toadd` varchar(255) DEFAULT NULL,
  `email_no` int(11) NOT NULL,
  `attach` varchar(255) DEFAULT NULL,
  `bcc_address` varchar(255) DEFAULT NULL,
  `cc_address` varchar(255) DEFAULT NULL,
  `email_body` varchar(255) DEFAULT NULL,
  `esno` int(11) DEFAULT NULL,
  `from_address` varchar(255) DEFAULT NULL,
  `sent` int(11) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `times_tamp` date DEFAULT NULL,
  `to_address` varchar(255) DEFAULT NULL,
  `upload_attach` char(1) DEFAULT NULL,
  `user_code` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `email_store`
--

INSERT INTO `email_store` (`emno`, `attch`, `ccadd`, `ebody`, `lockd`, `esolv`, `solvby`, `freze`, `fradd`, `locktime`, `manual`, `msgno`, `solvtime`, `sbjct`, `tstmp`, `toadd`, `email_no`, `attach`, `bcc_address`, `cc_address`, `email_body`, `esno`, `from_address`, `sent`, `subject`, `times_tamp`, `to_address`, `upload_attach`, `user_code`) VALUES
(2, '', '', 'kuch naya kar jao', 0, '\0', 0, 0, 'sales@burhanisolutions.com.pk', NULL, 0, 109, NULL, 'with api', NULL, 'isko20951@gmail.com', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `event_store`
--

CREATE TABLE `event_store` (
  `event_id` int(11) NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_on` date DEFAULT NULL,
  `event_from` date DEFAULT NULL,
  `event_to` date DEFAULT NULL,
  `cl_id` int(11) DEFAULT NULL,
  `event_closed_on` date DEFAULT NULL,
  `event_description` varchar(255) DEFAULT NULL,
  `event_all_day` int(11) DEFAULT NULL,
  `event_location` varchar(255) DEFAULT NULL,
  `event_notified` int(11) DEFAULT NULL,
  `event_status` int(11) DEFAULT NULL,
  `event_tittle` varchar(255) DEFAULT NULL,
  `freeze` int(11) DEFAULT NULL,
  `leads_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event_store`
--

INSERT INTO `event_store` (`event_id`, `created_by`, `created_on`, `event_from`, `event_to`, `cl_id`, `event_closed_on`, `event_description`, `event_all_day`, `event_location`, `event_notified`, `event_status`, `event_tittle`, `freeze`, `leads_id`, `product_id`) VALUES
(16, 1, '1969-12-31', '1969-12-31', '1969-12-31', 1, '1969-12-31', 'simple ', 0, 'Islamabad', 0, 0, 'Sample Event', 0, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(31),
(31),
(31);

-- --------------------------------------------------------

--
-- Table structure for table `leads_store`
--

CREATE TABLE `leads_store` (
  `leads_id` int(11) NOT NULL,
  `converted` int(11) DEFAULT NULL,
  `create_done` date DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `freeze` int(11) DEFAULT NULL,
  `leads_city` varchar(255) DEFAULT NULL,
  `leads_company_name` varchar(255) DEFAULT NULL,
  `leads_country` varchar(255) DEFAULT NULL,
  `leads_first_name` varchar(255) DEFAULT NULL,
  `leads_last_name` varchar(255) DEFAULT NULL,
  `leads_note` varchar(255) DEFAULT NULL,
  `leads_tittle` varchar(255) DEFAULT NULL,
  `leads_website` varchar(255) DEFAULT NULL,
  `s_id` int(11) DEFAULT NULL,
  `s_other` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leads_store`
--

INSERT INTO `leads_store` (`leads_id`, `converted`, `create_done`, `created_by`, `freeze`, `leads_city`, `leads_company_name`, `leads_country`, `leads_first_name`, `leads_last_name`, `leads_note`, `leads_tittle`, `leads_website`, `s_id`, `s_other`) VALUES
(17, 0, '1969-12-31', 0, 0, 'karachi', 'Burhani IT Solutions', 'Pakistan', 'Shakir', 'Hussain', 'some', 'not set', 'www.burhanisolutions.com.pk', 1, 'not set');

-- --------------------------------------------------------

--
-- Table structure for table `module_locking`
--

CREATE TABLE `module_locking` (
  `pm_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `locked_time` date DEFAULT NULL,
  `ps_id` int(11) DEFAULT NULL,
  `un_locked_time` date DEFAULT NULL,
  `user_code` int(11) DEFAULT NULL,
  `module_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `module_locking`
--

INSERT INTO `module_locking` (`pm_id`, `description`, `locked_time`, `ps_id`, `un_locked_time`, `user_code`, `module_id`) VALUES
(1, 'some', '1969-12-31', 1, '1969-12-31', 1, 18);

-- --------------------------------------------------------

--
-- Table structure for table `note_store`
--

CREATE TABLE `note_store` (
  `note_code` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `contact_id` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_on` date DEFAULT NULL,
  `leads_id` int(11) DEFAULT NULL,
  `n_id` int(11) DEFAULT NULL,
  `note_text` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `note_store`
--

INSERT INTO `note_store` (`note_code`, `client_id`, `contact_id`, `created_by`, `created_on`, `leads_id`, `n_id`, `note_text`, `product_id`) VALUES
(19, 1, 1, 1, '1969-12-31', 1, 1, 'some', 1);

-- --------------------------------------------------------

--
-- Table structure for table `notification_settings`
--

CREATE TABLE `notification_settings` (
  `notification_id` int(11) NOT NULL,
  `notification_event` int(11) DEFAULT NULL,
  `notification_task` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notification_settings`
--

INSERT INTO `notification_settings` (`notification_id`, `notification_event`, `notification_task`) VALUES
(20, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phone_list`
--

CREATE TABLE `phone_list` (
  `phone_id` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `contact_id` int(11) DEFAULT NULL,
  `leads_id` int(11) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `user_code` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `phone_list`
--

INSERT INTO `phone_list` (`phone_id`, `client_id`, `contact_id`, `leads_id`, `phone_number`, `user_code`) VALUES
(21, 1, 1, 1, '090078601', 1);

-- --------------------------------------------------------

--
-- Table structure for table `product_module`
--

CREATE TABLE `product_module` (
  `pm_id` int(11) NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_on` date DEFAULT NULL,
  `pm_description` varchar(255) DEFAULT NULL,
  `pm_name` varchar(255) DEFAULT NULL,
  `ps_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_module`
--

INSERT INTO `product_module` (`pm_id`, `created_by`, `created_on`, `pm_description`, `pm_name`, `ps_id`) VALUES
(22, 1, '1969-12-31', 'email handler', 'email', 1);

-- --------------------------------------------------------

--
-- Table structure for table `product_store`
--

CREATE TABLE `product_store` (
  `ps_id` int(11) NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_on` date DEFAULT NULL,
  `ps_description` varchar(255) DEFAULT NULL,
  `ps_name` varchar(255) DEFAULT NULL,
  `ps_price` int(11) DEFAULT NULL,
  `ps_property` int(11) DEFAULT NULL,
  `ps_started` date DEFAULT NULL,
  `ps_status` int(11) DEFAULT NULL,
  `ps_type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_store`
--

INSERT INTO `product_store` (`ps_id`, `created_by`, `created_on`, `ps_description`, `ps_name`, `ps_price`, `ps_property`, `ps_started`, `ps_status`, `ps_type`) VALUES
(23, 1, '1969-12-31', 'email handler', 'CRM', 100, 0, '1969-12-31', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `rights_chart`
--

CREATE TABLE `rights_chart` (
  `rights_chart_id` int(11) NOT NULL,
  `rights_code` int(11) DEFAULT NULL,
  `user_code` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rights_chart`
--

INSERT INTO `rights_chart` (`rights_chart_id`, `rights_code`, `user_code`) VALUES
(24, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `rights_list`
--

CREATE TABLE `rights_list` (
  `rights_code` int(11) NOT NULL,
  `rights_freeze` varchar(255) DEFAULT NULL,
  `rights_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rights_list`
--

INSERT INTO `rights_list` (`rights_code`, `rights_freeze`, `rights_name`) VALUES
(25, '1', 'Email Viewer'),
(26, '1', 'Email Viewer');

-- --------------------------------------------------------

--
-- Table structure for table `source_list`
--

CREATE TABLE `source_list` (
  `source_id` int(11) NOT NULL,
  `source_description` varchar(50) DEFAULT NULL,
  `source_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `source_list`
--

INSERT INTO `source_list` (`source_id`, `source_description`, `source_name`) VALUES
(29, 'Some how', 'website');

-- --------------------------------------------------------

--
-- Table structure for table `task_store`
--

CREATE TABLE `task_store` (
  `task_id` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `contact_id` int(11) DEFAULT NULL,
  `leads_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `task_closed_on` date DEFAULT NULL,
  `task_created_by` int(11) DEFAULT NULL,
  `task_created_on` date DEFAULT NULL,
  `task_description` varchar(50) DEFAULT NULL,
  `task_due_date` date DEFAULT NULL,
  `task_entry_date` date DEFAULT NULL,
  `task_freeze` int(11) DEFAULT NULL,
  `task_notified` int(11) DEFAULT NULL,
  `task_repeat` int(11) DEFAULT NULL,
  `task_status` int(11) DEFAULT NULL,
  `task_subject` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task_store`
--

INSERT INTO `task_store` (`task_id`, `client_id`, `contact_id`, `leads_id`, `product_id`, `task_closed_on`, `task_created_by`, `task_created_on`, `task_description`, `task_due_date`, `task_entry_date`, `task_freeze`, `task_notified`, `task_repeat`, `task_status`, `task_subject`) VALUES
(30, 1, 1, 1, 1, NULL, 1, NULL, 'kuch naya kar jao', NULL, NULL, 0, 0, 0, 1, 'file for NTN');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `ucode` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `freze` bit(1) DEFAULT NULL,
  `fname` varchar(255) DEFAULT NULL,
  `isemail` bit(1) DEFAULT NULL,
  `islog` int(11) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `uname` varchar(255) DEFAULT NULL,
  `upass` varchar(255) DEFAULT NULL,
  `uright` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ucode`, `email`, `freze`, `fname`, `isemail`, `islog`, `note`, `uname`, `upass`, `uright`) VALUES
(1, 'isko20951@gmail.com', b'1', 'Haroon Khan', b'0', 0, 'Super Admin', 'haroon12', 'haroon', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client_store`
--
ALTER TABLE `client_store`
  ADD PRIMARY KEY (`cl_id`);

--
-- Indexes for table `client_type`
--
ALTER TABLE `client_type`
  ADD PRIMARY KEY (`ct_code`);

--
-- Indexes for table `contact_store`
--
ALTER TABLE `contact_store`
  ADD PRIMARY KEY (`cs_id`);

--
-- Indexes for table `domain_list`
--
ALTER TABLE `domain_list`
  ADD PRIMARY KEY (`domain_code`);

--
-- Indexes for table `email_general`
--
ALTER TABLE `email_general`
  ADD PRIMARY KEY (`email_no`);

--
-- Indexes for table `email_list`
--
ALTER TABLE `email_list`
  ADD PRIMARY KEY (`email_id`);

--
-- Indexes for table `email_relation`
--
ALTER TABLE `email_relation`
  ADD PRIMARY KEY (`email_no`);

--
-- Indexes for table `email_sent`
--
ALTER TABLE `email_sent`
  ADD PRIMARY KEY (`email_no`);

--
-- Indexes for table `email_store`
--
ALTER TABLE `email_store`
  ADD PRIMARY KEY (`emno`);

--
-- Indexes for table `event_store`
--
ALTER TABLE `event_store`
  ADD PRIMARY KEY (`event_id`);

--
-- Indexes for table `leads_store`
--
ALTER TABLE `leads_store`
  ADD PRIMARY KEY (`leads_id`);

--
-- Indexes for table `module_locking`
--
ALTER TABLE `module_locking`
  ADD PRIMARY KEY (`pm_id`);

--
-- Indexes for table `note_store`
--
ALTER TABLE `note_store`
  ADD PRIMARY KEY (`note_code`);

--
-- Indexes for table `notification_settings`
--
ALTER TABLE `notification_settings`
  ADD PRIMARY KEY (`notification_id`);

--
-- Indexes for table `phone_list`
--
ALTER TABLE `phone_list`
  ADD PRIMARY KEY (`phone_id`);

--
-- Indexes for table `product_module`
--
ALTER TABLE `product_module`
  ADD PRIMARY KEY (`pm_id`);

--
-- Indexes for table `product_store`
--
ALTER TABLE `product_store`
  ADD PRIMARY KEY (`ps_id`);

--
-- Indexes for table `rights_chart`
--
ALTER TABLE `rights_chart`
  ADD PRIMARY KEY (`rights_chart_id`);

--
-- Indexes for table `rights_list`
--
ALTER TABLE `rights_list`
  ADD PRIMARY KEY (`rights_code`);

--
-- Indexes for table `source_list`
--
ALTER TABLE `source_list`
  ADD PRIMARY KEY (`source_id`);

--
-- Indexes for table `task_store`
--
ALTER TABLE `task_store`
  ADD PRIMARY KEY (`task_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ucode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
