SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('clusteredScheduler', 'TaskJob', 'cms', '0/5 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('clusteredScheduler', 'email_verify_code', 'cms', '0/5 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('clusteredScheduler', 'TaskJob', 'cms', NULL, 'com.xzjie.cms.quartz.task.TaskJob', '0', '0', '0', '0', 0x230A23547565204175672031372031313A33323A32342043535420323032310A);
INSERT INTO `qrtz_job_details` VALUES ('clusteredScheduler', 'email_verify_code', 'cms', NULL, 'com.xzjie.cms.quartz.task.TaskJob', '0', '0', '0', '0', 0x230A23547565204175672031372031313A33323A32332043535420323032310A);

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('clusteredScheduler', 'TaskJob', 'cms', 'TaskJob', 'cms', NULL, 1629173350000, 1629173345000, 5, 'ERROR', 'CRON', 1629171144000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('clusteredScheduler', 'email_verify_code', 'cms', 'email_verify_code', 'cms', NULL, 1629173345000, 1629173340000, 5, 'ERROR', 'CRON', 1629171143000, 0, NULL, 2, '');

SET FOREIGN_KEY_CHECKS = 1;