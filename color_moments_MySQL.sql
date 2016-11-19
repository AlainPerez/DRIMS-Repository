/*
Navicat MySQL Data Transfer

Source Server         : jjj
Source Server Version : 50613
Source Host           : localhost:13306
Source Database       : UCI_colorMoments

Target Server Type    : MYSQL
Target Server Version : 50613
File Encoding         : 65001

Date: 2016-11-18 23:37:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for color_moments
-- ----------------------------
DROP TABLE IF EXISTS `color_moments`;
CREATE TABLE `color_moments` (
  `id` int(11) NOT NULL DEFAULT '0',
  `hmean` float DEFAULT NULL,
  `hstdv` float DEFAULT NULL,
  `hskew` float DEFAULT NULL,
  `smean` float DEFAULT NULL,
  `sstdv` float DEFAULT NULL,
  `sskew` float DEFAULT NULL,
  `vmean` float DEFAULT NULL,
  `vstdv` float DEFAULT NULL,
  `vskew` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of color_moments
-- ----------------------------
INSERT INTO `color_moments` VALUES ('3333', '-0.243527', '0.276054', '-0.096808', '-0.575394', '-0.456805', '-0.196377', '0.820875', '0.857738', '0.841658');
INSERT INTO `color_moments` VALUES ('3334', '-0.920985', '-0.858713', '-0.953262', '-1.17627', '0.067915', '0.727488', '-0.943218', '-0.418123', '-0.536684');
INSERT INTO `color_moments` VALUES ('3335', '-1.51245', '-1.39664', '-1.33551', '-1.12263', '-0.300861', '0.104257', '-0.492131', '-0.30489', '-0.45556');
INSERT INTO `color_moments` VALUES ('3336', '-1.29637', '-0.400324', '-0.351176', '-2.11889', '-2.25938', '-2.12449', '0.637118', '1.81383', '1.56255');
INSERT INTO `color_moments` VALUES ('3337', '0.47789', '0.125632', '-0.035516', '-1.04549', '-0.308556', '0.168233', '0.935458', '1.76104', '1.67999');
INSERT INTO `color_moments` VALUES ('3338', '-0.491583', '0.498432', '0.099102', '-1.05449', '-1.17717', '-1.2228', '1.14857', '0.130932', '0.031511');
INSERT INTO `color_moments` VALUES ('3339', '-0.556101', '0.567847', '0.204433', '-1.12037', '-1.10788', '-0.67751', '0.778424', '0.628075', '0.466057');
INSERT INTO `color_moments` VALUES ('3340', '-1.69815', '-1.63803', '-1.41241', '-1.45561', '-0.68084', '-0.047594', '-0.197227', '-0.256368', '-0.359568');
INSERT INTO `color_moments` VALUES ('3341', '0.643289', '0.395868', '0.119287', '-0.56596', '0.71018', '0.771316', '0.31864', '0.898816', '0.779209');
INSERT INTO `color_moments` VALUES ('3342', '0.186766', '0.312083', '-0.105764', '-0.416633', '-0.448803', '-0.337957', '0.117807', '-0.409026', '-0.616738');
INSERT INTO `color_moments` VALUES ('3343', '1.08248', '1.25106', '1.21259', '-0.691537', '0.100074', '-0.07971', '0.398075', '-0.357449', '-0.475384');
INSERT INTO `color_moments` VALUES ('3344', '1.91365', '-0.385883', '-0.102879', '-1.25224', '-0.284433', '-0.467363', '0.5176', '-0.055548', '-0.249112');
INSERT INTO `color_moments` VALUES ('3345', '-0.073036', '1.13424', '0.880381', '-1.76102', '-1.99207', '-2.15411', '-0.01106', '0.31124', '0.424084');
INSERT INTO `color_moments` VALUES ('3346', '-1.0859', '1.02519', '1.31875', '-1.48726', '-1.13674', '-1.07557', '-0.469921', '0.309273', '0.542861');
INSERT INTO `color_moments` VALUES ('3347', '-1.2402', '-0.738234', '-0.52353', '-0.681307', '0.254513', '0.465152', '-0.720863', '1.0131', '1.19128');
INSERT INTO `color_moments` VALUES ('3348', '1.18972', '-0.037017', '-0.06959', '-0.85707', '-0.555928', '-0.341473', '0.198593', '0.344041', '0.422007');
INSERT INTO `color_moments` VALUES ('3349', '-0.365339', '0.072106', '-0.181212', '-0.04038', '1.37475', '1.37805', '0.121766', '1.55132', '1.37662');
INSERT INTO `color_moments` VALUES ('3350', '0.34701', '-0.028694', '-0.264491', '-1.29623', '-0.576197', '-0.203657', '1.05977', '1.35275', '1.33881');
INSERT INTO `color_moments` VALUES ('3351', '-0.202265', '-0.352888', '-0.664775', '-1.4001', '-0.65648', '-0.084414', '0.434354', '1.65429', '1.42751');
INSERT INTO `color_moments` VALUES ('3352', '-0.183661', '0.048698', '-0.040851', '-0.672922', '1.13507', '1.48955', '-0.531054', '1.91603', '2.02767');
INSERT INTO `color_moments` VALUES ('3353', '-0.26541', '0.247496', '-0.065478', '-0.255915', '0.823278', '0.991764', '-0.233538', '1.24269', '1.14911');
INSERT INTO `color_moments` VALUES ('3354', '-0.81941', '-0.045995', '-0.24906', '-0.42281', '1.20334', '1.28964', '0.310661', '1.03236', '0.893348');
INSERT INTO `color_moments` VALUES ('3355', '0.282193', '-0.393297', '-0.762677', '-0.422367', '-0.007085', '0.148479', '0.480984', '1.65291', '1.4318');
INSERT INTO `color_moments` VALUES ('3356', '0.947663', '-0.415308', '-0.604921', '-0.831076', '-0.118218', '0.093379', '1.0566', '0.199724', '0.176415');
INSERT INTO `color_moments` VALUES ('3357', '0.388096', '0.060309', '-0.173485', '-1.61948', '-0.883505', '-0.589036', '0.14356', '1.82606', '1.67471');
INSERT INTO `color_moments` VALUES ('3358', '-1.11861', '1.11118', '1.54493', '-1.55189', '0.105144', '0.634375', '0.821744', '1.95929', '1.84527');
INSERT INTO `color_moments` VALUES ('3359', '-0.389419', '1.00881', '0.872983', '-1.88572', '-2.20707', '-2.16931', '0.213154', '1.10768', '0.93809');
INSERT INTO `color_moments` VALUES ('3360', '0.314195', '0.331707', '0.167879', '-1.64257', '-1.65527', '-1.6426', '0.476529', '0.589973', '0.424344');
INSERT INTO `color_moments` VALUES ('3361', '-0.372067', '0.246625', '0.193576', '-1.24821', '-1.02469', '-1.11066', '0.186558', '0.568633', '0.481035');
INSERT INTO `color_moments` VALUES ('3362', '-0.233857', '-0.413108', '-0.62609', '-1.71817', '-1.10275', '-0.584948', '-0.691861', '0.518435', '0.704329');
INSERT INTO `color_moments` VALUES ('3363', '-0.3815', '0.838543', '0.645636', '-1.45804', '-0.482278', '-0.19078', '-0.738855', '1.37181', '1.73573');
INSERT INTO `color_moments` VALUES ('3364', '-1.00589', '-0.372928', '-0.339677', '-1.14074', '0.618515', '1.14165', '-0.825575', '0.644642', '0.707171');
INSERT INTO `color_moments` VALUES ('3365', '-0.715808', '-0.329064', '-0.614521', '-1.62971', '-1.22019', '-0.729888', '-0.05612', '1.45092', '1.398');
INSERT INTO `color_moments` VALUES ('3366', '-0.41632', '-0.551129', '-0.814409', '-1.9487', '-2.30561', '-2.30941', '0.60757', '0.910119', '0.667496');
INSERT INTO `color_moments` VALUES ('3367', '-0.005614', '0.667894', '0.386848', '-1.37153', '-1.28979', '-1.09808', '0.729665', '0.057501', '-0.062764');
INSERT INTO `color_moments` VALUES ('3368', '-0.075668', '0.728489', '0.447775', '-1.56923', '-1.3218', '-1.09349', '0.818393', '0.355896', '0.240538');
INSERT INTO `color_moments` VALUES ('3369', '0.201513', '-0.217218', '-0.549589', '-1.11397', '-1.34336', '-1.4636', '0.662663', '-0.363526', '-0.49164');
INSERT INTO `color_moments` VALUES ('3370', '0.634276', '0.377784', '0.16094', '-1.54975', '-1.18895', '-0.869129', '0.940856', '0.817848', '0.625272');
INSERT INTO `color_moments` VALUES ('3371', '-1.40972', '-0.689296', '-0.59282', '-0.750898', '0.066968', '0.282099', '-0.008104', '0.558521', '0.626287');
INSERT INTO `color_moments` VALUES ('3372', '-1.13926', '0.124275', '0.280296', '-1.10321', '-0.207251', '-0.065966', '0.094409', '0.779387', '0.780774');
INSERT INTO `color_moments` VALUES ('3373', '-0.587552', '-0.433508', '-0.487959', '-1.16185', '0.355529', '0.895468', '-0.859488', '0.792293', '0.716995');
INSERT INTO `color_moments` VALUES ('3374', '-0.526978', '0.070423', '-0.054369', '-1.66997', '-0.580316', '-0.052939', '-0.059814', '0.756685', '0.578462');
INSERT INTO `color_moments` VALUES ('3375', '-1.68548', '-0.899871', '-0.555973', '-1.21434', '-0.556829', '-0.194341', '0.16512', '1.10852', '0.978912');
INSERT INTO `color_moments` VALUES ('3376', '0.774497', '1.79333', '1.52527', '-2.05386', '-2.18158', '-2.18249', '0.463423', '0.545776', '0.30839');
INSERT INTO `color_moments` VALUES ('3377', '0.46981', '1.24014', '1.00898', '-2.13579', '-2.37944', '-2.0669', '-0.313968', '-0.480082', '-0.689932');
INSERT INTO `color_moments` VALUES ('3378', '-0.683328', '1.12514', '1.22287', '-1.53061', '-0.87258', '-0.665981', '0.429119', '0.582014', '0.484937');
INSERT INTO `color_moments` VALUES ('3379', '-0.913076', '0.405297', '0.277101', '-1.49736', '-1.39699', '-1.19951', '0.247024', '0.355091', '0.270698');
INSERT INTO `color_moments` VALUES ('3380', '-0.270115', '0.506111', '0.141142', '-1.71671', '-1.53202', '-1.13034', '0.611358', '0.501542', '0.382145');
INSERT INTO `color_moments` VALUES ('3381', '-0.962739', '0.034362', '-0.089844', '-1.63324', '-1.46265', '-1.37847', '0.084116', '1.51193', '1.43466');
INSERT INTO `color_moments` VALUES ('3382', '-1.3388', '-0.559829', '-0.321954', '-0.981889', '0.264582', '0.305139', '0.66924', '1.05164', '0.943775');
INSERT INTO `color_moments` VALUES ('3383', '-1.37982', '-1.92054', '-2.02226', '-0.745873', '-1.1391', '-1.23142', '0.189969', '-1.27033', '-1.52086');
INSERT INTO `color_moments` VALUES ('3384', '-1.54673', '-2.09313', '-2.40314', '-1.13187', '-1.20951', '-0.991516', '-0.041419', '0.484278', '0.286316');
INSERT INTO `color_moments` VALUES ('3385', '-1.55219', '-0.727015', '-0.409757', '-0.951604', '-0.952174', '-0.732975', '-0.739817', '-0.003596', '-0.052946');
INSERT INTO `color_moments` VALUES ('3386', '-1.03982', '-0.318414', '-0.330069', '-1.69938', '-1.32226', '-1.42501', '0.371455', '1.64056', '1.50304');
INSERT INTO `color_moments` VALUES ('3387', '-1.2239', '-1.10147', '-0.934423', '-1.41232', '-1.75556', '-2.01243', '-0.26497', '0.315184', '0.567612');
INSERT INTO `color_moments` VALUES ('3388', '-0.00932', '0.264729', '-0.056532', '-1.17366', '-0.831793', '-0.958852', '0.838327', '0.670738', '0.479934');
INSERT INTO `color_moments` VALUES ('3389', '-0.575953', '0.14122', '0.209666', '-1.97707', '-2.02913', '-1.85405', '0.453194', '1.37263', '1.21269');
INSERT INTO `color_moments` VALUES ('3390', '-0.368781', '-0.151399', '-0.494052', '-1.56404', '-1.51809', '-1.64616', '0.452305', '2.09904', '1.82263');
INSERT INTO `color_moments` VALUES ('3391', '-0.0579', '0.271983', '0.059851', '-1.62934', '-1.80514', '-1.76518', '0.556835', '1.3982', '1.0998');
INSERT INTO `color_moments` VALUES ('3392', '-0.493537', '0.082594', '-0.271055', '-0.810838', '-0.763707', '-0.923385', '0.278809', '1.15553', '0.913397');
INSERT INTO `color_moments` VALUES ('3393', '-0.457692', '0.223436', '-0.132179', '-0.545891', '-0.796525', '-0.91929', '0.590068', '0.739543', '0.578666');
INSERT INTO `color_moments` VALUES ('3394', '-0.483387', '0.437186', '0.076613', '-0.150989', '-0.721954', '-0.835365', '0.400527', '0.407488', '0.129972');
INSERT INTO `color_moments` VALUES ('3395', '-0.754578', '-0.26668', '-0.414923', '-0.931799', '-0.329801', '-0.508665', '0.664455', '0.80394', '0.692102');
INSERT INTO `color_moments` VALUES ('3396', '-0.161987', '0.162393', '-0.279237', '-0.358048', '-0.924848', '-0.88378', '0.949915', '-0.020868', '-0.042895');
INSERT INTO `color_moments` VALUES ('3397', '-0.225638', '0.079015', '-0.315208', '0.128671', '-0.53291', '-0.497372', '0.68542', '0.16756', '-0.116509');
INSERT INTO `color_moments` VALUES ('3398', '-1.63705', '-1.62183', '-1.41887', '-0.301754', '-0.43066', '-0.285977', '-0.176346', '-0.634953', '-0.770952');
INSERT INTO `color_moments` VALUES ('3399', '0.74571', '-0.402468', '-0.58682', '-0.161567', '0.427364', '0.247622', '0.03609', '-0.071529', '-0.114587');
INSERT INTO `color_moments` VALUES ('3400', '-0.096018', '0.24367', '-0.17175', '0.153888', '0.424853', '0.248133', '-0.110758', '0.125441', '0.153133');
INSERT INTO `color_moments` VALUES ('3401', '-0.008126', '0.527351', '0.387655', '-1.86761', '-1.81756', '-1.46152', '2.44231', '-0.724559', '-0.063905');
INSERT INTO `color_moments` VALUES ('3402', '1.3978', '-1.13396', '-0.935738', '-1.28179', '-0.882393', '-0.38992', '1.76906', '-0.270116', '0.149698');
INSERT INTO `color_moments` VALUES ('3403', '1.31541', '-1.72208', '-1.57331', '-1.15006', '-2.4382', '-2.13822', '2.14143', '-1.81583', '-1.27862');
INSERT INTO `color_moments` VALUES ('3404', '1.42407', '-0.596559', '-0.417177', '-1.81', '-2.12075', '-2.03145', '2.05722', '-0.578103', '-0.058592');
INSERT INTO `color_moments` VALUES ('3405', '1.61396', '-1.5316', '-1.31442', '-1.15375', '-2.11574', '-2.12854', '2.28292', '-1.7225', '-1.32872');
INSERT INTO `color_moments` VALUES ('3406', '1.05359', '-1.45089', '-1.348', '-0.407023', '-1.75347', '-1.64741', '2.66998', '-1.53623', '-0.740723');
INSERT INTO `color_moments` VALUES ('3407', '1.19242', '-0.707161', '-0.677434', '-0.422547', '0.181734', '0.070858', '0.790275', '1.89589', '1.77244');
INSERT INTO `color_moments` VALUES ('3408', '1.75258', '-1.37644', '-1.18282', '-1.21377', '-1.22662', '-1.3343', '0.796148', '-1.22514', '-1.27752');
INSERT INTO `color_moments` VALUES ('3409', '1.31756', '-0.911831', '-0.649052', '0.944338', '-1.60368', '-1.5255', '1.60834', '-0.877429', '-0.343616');
INSERT INTO `color_moments` VALUES ('3410', '1.2781', '-0.966834', '-0.659023', '-2.13029', '-2.56144', '-2.37912', '2.76674', '-1.78503', '-1.03574');
INSERT INTO `color_moments` VALUES ('3411', '1.47389', '-1.12523', '-0.855275', '-1.08418', '-1.63878', '-1.30538', '2.50936', '0.160686', '1.1256');
INSERT INTO `color_moments` VALUES ('3412', '1.32465', '-1.01056', '-0.797009', '-0.517456', '-1.41699', '-1.4822', '1.74139', '1.00333', '1.3085');
INSERT INTO `color_moments` VALUES ('3413', '-0.035572', '0.28558', '-0.010338', '-2.31533', '-2.8261', '-2.47014', '2.77008', '-2.37167', '-1.8895');
INSERT INTO `color_moments` VALUES ('3414', '0.175791', '2.29043', '2.00836', '1.24628', '-0.149608', '-0.415042', '0.20265', '0.639874', '0.647433');
INSERT INTO `color_moments` VALUES ('3415', '1.49705', '-0.875532', '-0.618739', '-1.97006', '-2.15129', '-1.76889', '2.26646', '-0.928789', '-0.389454');
INSERT INTO `color_moments` VALUES ('3416', '1.00571', '0.147827', '-0.014277', '-1.22629', '1.22174', '1.24802', '1.60845', '0.42912', '0.29551');
INSERT INTO `color_moments` VALUES ('3417', '-0.003005', '0.15693', '-0.214463', '-0.220601', '0.413261', '0.099107', '0.10975', '-0.105199', '-0.312931');
INSERT INTO `color_moments` VALUES ('3418', '-0.434404', '-0.841311', '-0.730541', '-0.742729', '0.048719', '0.275759', '-0.074229', '0.97892', '0.937824');
INSERT INTO `color_moments` VALUES ('3419', '0.559572', '-0.504551', '-0.53029', '1.96868', '-0.725337', '-0.685041', '0.932863', '2.72137', '2.60906');
INSERT INTO `color_moments` VALUES ('3420', '1.58349', '-0.232034', '0.015869', '-1.02319', '-0.760356', '-0.917426', '-1.44516', '-1.74267', '-1.51288');
INSERT INTO `color_moments` VALUES ('3421', '1.02263', '-0.89984', '-0.776856', '-0.325714', '-0.340545', '-0.593524', '2.57157', '-2.23807', '-1.80672');
INSERT INTO `color_moments` VALUES ('3422', '1.25681', '-1.61985', '-1.4794', '-1.73768', '-1.62324', '-1.15898', '2.32648', '-0.479662', '0.196885');
INSERT INTO `color_moments` VALUES ('3423', '0.672173', '-0.65538', '-0.510056', '-1.55974', '-1.41028', '-0.955208', '2.48927', '-1.40457', '-0.752152');
INSERT INTO `color_moments` VALUES ('3424', '1.16711', '-0.913442', '-0.589271', '-1.69984', '-2.0688', '-1.62518', '2.73076', '-0.904464', '0.150094');
INSERT INTO `color_moments` VALUES ('3425', '1.11506', '-0.44746', '-0.296722', '-0.757388', '-0.175124', '-0.224984', '0.412689', '1.72175', '1.46065');
INSERT INTO `color_moments` VALUES ('3426', '1.93955', '0.321723', '0.468309', '-1.09412', '-0.431147', '-0.679092', '1.90931', '-1.3439', '-1.11794');
INSERT INTO `color_moments` VALUES ('3427', '-0.012994', '-0.603763', '-0.83136', '-0.042458', '-0.327043', '-0.517992', '-1.0052', '-0.590007', '-0.708206');
INSERT INTO `color_moments` VALUES ('3428', '1.82649', '0.007547', '0.117359', '-0.397536', '0.134779', '-0.04624', '1.765', '-1.22958', '-1.60127');
INSERT INTO `color_moments` VALUES ('3429', '1.17597', '-1.21738', '-0.949375', '2.59674', '0.404117', '0.922102', '2.36956', '-0.803447', '-0.260269');
INSERT INTO `color_moments` VALUES ('3430', '1.22114', '-1.72313', '-1.68273', '-0.36875', '-2.22408', '-2.36013', '2.71212', '-2.87374', '-2.48399');
INSERT INTO `color_moments` VALUES ('3431', '-0.105895', '-0.656831', '-0.85372', '-0.941055', '-1.69892', '-1.94546', '-1.04833', '-1.14009', '-1.00117');
INSERT INTO `color_moments` VALUES ('3432', '1.18718', '-2.02465', '-2.19229', '-1.1983', '-2.0043', '-2.47693', '2.21842', '-2.72749', '-2.78071');
SET FOREIGN_KEY_CHECKS=1;
