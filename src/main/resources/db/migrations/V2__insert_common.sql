INSERT INTO users (id, name, first_name, last_name, phone_number, avatar, email, country, address, date_of_birth, active, password, role)
VALUES
(
    1, 'admin01', NULL, NULL, NULL,
    'https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/UNKNOWN_USER.PNG?alt=media&token=5a0b3bba-f852-491c-8f50-c532be5ca4b0',
    'admin01@hanoitravel.com.vn', NULL, NULL, '2002-10-08', 1,
    '$2a$10$xtiuHyOW7CRExUx0dt9wquac2hDwiXzsZkPyYsp0HlQh5oB7DChqi', 'ADMIN'
),
(
    2, 'ddthumonky0810', 'Do', 'Duc Thu', NULL,
    'https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/UNKNOWN_USER.PNG?alt=media&token=5a0b3bba-f852-491c-8f50-c532be5ca4b0',
    'ddthumonky0810@gmail.com', 'VN', NULL, '2002-10-08', 1,
    '$2a$10$xtiuHyOW7CRExUx0dt9wquac2hDwiXzsZkPyYsp0HlQh5oB7DChqi', 'USER'
),
(
    3, 'daoquangduy441', 'Dao', 'Quang Duy', NULL,
    'https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/UNKNOWN_USER.PNG?alt=media&token=5a0b3bba-f852-491c-8f50-c532be5ca4b0',
    'daoquangduy441@gmail.com', 'VN', NULL, NULL, 1,
    '$2a$10$xtiuHyOW7CRExUx0dt9wquac2hDwiXzsZkPyYsp0HlQh5oB7DChqi', 'USER'
),
(
    4, 'vuxuanhoai28', 'Vu', 'Xuan Hoai', NULL,
    'https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/UNKNOWN_USER.PNG?alt=media&token=5a0b3bba-f852-491c-8f50-c532be5ca4b0',
    'vuxuanhoai28@gmail.com', 'VN', NULL, NULL, 1,
    '$2a$10$xtiuHyOW7CRExUx0dt9wquac2hDwiXzsZkPyYsp0HlQh5oB7DChqi', 'USER'
);

INSERT INTO city (id, city_name, type, parent_id)
VALUES
-- Thành phố trực thuộc TW
(29, 'Hà Nội', 'thành phố', NULL),
(41, 'Hồ Chí Minh', 'thành phố', NULL),
(43, 'Đà Nẵng', 'thành phố', NULL),
(15, 'Hải Phòng', 'thành phố', NULL),
(65, 'Cần Thơ', 'thành phố', NULL),
(75, 'Huế', 'thành phố', NULL),
-- Các tỉnh còn lại
(67, 'An Giang', 'tỉnh', NULL),
(72, 'Bà Rịa-Vũng Tàu', 'tỉnh', NULL),
(98, 'Bắc Giang', 'tỉnh', NULL),
(97, 'Bắc Kạn', 'tỉnh', NULL),
(94, 'Bạc Liêu', 'tỉnh', NULL),
(99, 'Bắc Ninh', 'tỉnh', NULL),
(71, 'Bến Tre', 'tỉnh', NULL),
(77, 'Bình Định', 'tỉnh', NULL),
(61, 'Bình Dương', 'tỉnh', NULL),
(93, 'Bình Phước', 'tỉnh', NULL),
(86, 'Bình Thuận', 'tỉnh', NULL),
(69, 'Cà Mau', 'tỉnh', NULL),
(11, 'Cao Bằng', 'tỉnh', NULL),
(47, 'Đắk Lắk', 'tỉnh', NULL),
(48, 'Đắk Nông', 'tỉnh', NULL),
(27, 'Điện Biên', 'tỉnh', NULL),
(60, 'Đồng Nai', 'tỉnh', NULL),
(66, 'Đồng Tháp', 'tỉnh', NULL),
(81, 'Gia Lai', 'tỉnh', NULL),
(23, 'Hà Giang', 'tỉnh', NULL),
(90, 'Hà Nam', 'tỉnh', NULL),
(38, 'Hà Tĩnh', 'tỉnh', NULL),
(34, 'Hải Dương', 'tỉnh', NULL),
(95, 'Hậu Giang', 'tỉnh', NULL),
(28, 'Hòa Bình', 'tỉnh', NULL),
(89, 'Hưng Yên', 'tỉnh', NULL),
(79, 'Khánh Hòa', 'tỉnh', NULL),
(68, 'Kiên Giang', 'tỉnh', NULL),
(82, 'Kon Tum', 'tỉnh', NULL),
(25, 'Lai Châu', 'tỉnh', NULL),
(49, 'Lâm Đồng', 'tỉnh', NULL),
(12, 'Lạng Sơn', 'tỉnh', NULL),
(24, 'Lào Cai', 'tỉnh', NULL),
(62, 'Long An', 'tỉnh', NULL),
(18, 'Nam Định', 'tỉnh', NULL),
(37, 'Nghệ An', 'tỉnh', NULL),
(35, 'Ninh Bình', 'tỉnh', NULL),
(85, 'Ninh Thuận', 'tỉnh', NULL),
(19, 'Phú Thọ', 'tỉnh', NULL),
(78, 'Phú Yên', 'tỉnh', NULL),
(73, 'Quảng Bình', 'tỉnh', NULL),
(92, 'Quảng Nam', 'tỉnh', NULL),
(76, 'Quảng Ngãi', 'tỉnh', NULL),
(14, 'Quảng Ninh', 'tỉnh', NULL),
(74, 'Quảng Trị', 'tỉnh', NULL),
(83, 'Sóc Trăng', 'tỉnh', NULL),
(26, 'Sơn La', 'tỉnh', NULL),
(70, 'Tây Ninh', 'tỉnh', NULL),
(17, 'Thái Bình', 'tỉnh', NULL),
(20, 'Thái Nguyên', 'tỉnh', NULL),
(36, 'Thanh Hóa', 'tỉnh', NULL),
(63, 'Tiền Giang', 'tỉnh', NULL),
(84, 'Trà Vinh', 'tỉnh', NULL),
(22, 'Tuyên Quang', 'tỉnh', NULL),
(64, 'Vĩnh Long', 'tỉnh', NULL),
(88, 'Vĩnh Phúc', 'tỉnh', NULL),
(21, 'Yên Bái', 'tỉnh', NULL);

INSERT INTO attraction (name, address, type, image, latitude, longitude, city_id) VALUES
('Văn Phòng Mouse Rental tại Hà Nội', NULL, 'office', NULL, 21.0278, 105.8342, 29),
('Văn Phòng Mouse Rental tại TPHCM', NULL, 'office', NULL, 10.7769, 106.6951, 41),
('Văn Phòng Mouse Rental tại Hải Phòng', NULL, 'office', NULL, 20.8448, 106.6888, 15),
('Văn Phòng Buffalo Rental tại Hà Nội', NULL, 'office', NULL, 21.0285, 105.8542, 29),
('Văn Phòng Buffalo Rental tại Đà Nẵng', NULL, 'office', NULL, 16.0671, 108.2208, 43),
('Văn Phòng Buffalo Rental tại Cần Thơ', NULL, 'office', NULL, 10.0340, 105.7830, 65),
('Văn Phòng Tiger Rental tại TPHCM', NULL, 'office', NULL, 10.7770, 106.6952, 41),
('Văn Phòng Tiger Rental tại Đà Nẵng', NULL, 'office', NULL, 16.0672, 108.2209, 43),
('Văn Phòng Tiger Rental tại Huế', NULL, 'office', NULL, 16.4644, 107.5785, 75),
('Văn Phòng Cat Rental tại Hà Nội', NULL, 'office', NULL, 21.0290, 105.8550, 29),
('Văn Phòng Cat Rental tại Hải Phòng', NULL, 'office', NULL, 20.8450, 106.6890, 15),
('Văn Phòng Cat Rental tại Cần Thơ', NULL, 'office', NULL, 10.0953, 105.7714, 65),
('Văn Phòng Dragon Rental tại Đà Nẵng', NULL, 'office', NULL, 16.0673, 108.2210, 43),
('Văn Phòng Dragon Rental tại TPHCM', NULL, 'office', NULL, 10.7771, 106.6953, 41),
('Văn Phòng Dragon Rental tại Huế', NULL, 'office', NULL, 16.4640, 107.5790, 75),
('Văn Phòng Snake Rental tại Hải Phòng', NULL, 'office', NULL, 20.8440, 106.6870, 15),
('Văn Phòng Snake Rental tại Cần Thơ', NULL, 'office', NULL, 10.0950, 105.7700, 65),
('Văn Phòng Snake Rental tại Đà Nẵng', NULL, 'office', NULL, 16.0674, 108.2211, 43),
('Văn Phòng Horse Rental tại Hà Nội', NULL, 'office', NULL, 21.0300, 105.8560, 29),
('Văn Phòng Horse Rental tại TPHCM', NULL, 'office', NULL, 10.7772, 106.6954, 41),
('Văn Phòng Horse Rental tại Huế', NULL, 'office', NULL, 16.4645, 107.5795, 75),
('Văn Phòng Goat Rental tại Đà Nẵng', NULL, 'office', NULL, 16.0675, 108.2212, 43),
('Văn Phòng Goat Rental tại Hải Phòng', NULL, 'office', NULL, 20.8451, 106.6891, 15),
('Văn Phòng Goat Rental tại Cần Thơ', NULL, 'office', NULL, 10.0955, 105.7715, 65),
('Văn Phòng Thái Bình tại Thái Bình', NULL, 'office', NULL, 20.449956828264547, 106.33307310262715, 17),
(
    'Sân bay quốc tế Cam Ranh (CXR)',
    'Cam Ranh, Khánh Hòa', 'airport', NULL,
    11.998556790517712, 109.22070838179276, 79
),
(
    'Sân bay quốc tế Cần Thơ (VCA)',
    'Cần Thơ', 'airport', NULL,
    10.080662638930567, 105.7130251459081, 65
),
(
    'Sân bay quốc tế Đà Nẵng (DAD)',
    'Đà Nẵng', 'airport', NULL,
    16.070284113204004, 108.24617634660565, 43
),
(
    'Sân bay quốc tế Nội Bài (HAN)',
    'Hà Nội', 'airport', NULL,
    21.21551052996324, 105.78980573778564, 29
),
(
    'Sân bay quốc tế Cát Bi (HPH)',
    'Hải Phòng', 'airport', NULL,
    20.81892405995717, 106.72399621980605, 15
),
(
    'Sân bay quốc tế Phú Quốc (PQC)',
    'Phú Quốc, Kiên Giang', 'airport', NULL,
    10.163048644498208, 103.99872872353349, 68
),
(
    'Sân bay quốc tế Vân Đồn (VDO)',
    'Vân Đồn, Quảng Ninh', 'airport', NULL,
    21.120536267507006, 107.41651767770436, 14
),
(
    'Sân bay quốc tế Tân Sơn Nhất (SGN)',
    'TP.HCM', 'airport', NULL,
    10.815970316474269, 106.66502208280289, 41
),
(
    'Sân bay quốc tế Vinh',
    'Vinh, Nghệ An', 'airport', NULL,
    18.727863206378387, 105.66928765251403, 37
),
(
    'Sân vận động Quốc gia Mỹ Đình',
    'Nam Tu Liem, Hà Nội', 'stadium', NULL,
    21.02072383698477, 105.7647562446359, 29
),
(
    'Sân vận động Thống Nhất',
    'TP.HCM', 'stadium', NULL,
    10.760874208123209, 106.66378859126175, 41
),
(
    'Sân vận động Hàng Đẫy',
    'Hà Nội', 'stadium', NULL,
    21.030035250820667, 105.83408459437376, 29
),
(
    'Sân vận động Lạch Tray',
    'Hải Phòng', 'stadium', NULL,
    20.851927746706547, 106.69012877109022, 15
),
(
    'Sân vận động Gò Đậu',
    'Thủ Dầu Một, Bình Dương', 'stadium', NULL,
    10.970577282313677, 106.67195946741992, 61
),
(
    'Sân vận động Thanh Hóa',
    'Thanh Hóa, Thanh Hóa', 'stadium', NULL,
    19.799357889794578, 105.77396220968677, 36
),
(
    'Sân vận động Pleiku',
    'Pleiku, Gia Lai', 'stadium', NULL,
    13.982697249597257, 108.01666761971181, 81
),
(
    'Sân vận động Quy Nhơn',
    'Quy Nhơn, Bình Định', 'stadium', NULL,
    13.77464405768291, 109.2278175785767, 77
),
(
    'Sân vận động Vinh',
    'Vinh, Nghệ An', 'stadium', NULL,
    18.6691745464956, 105.6711215667224, 37
),
(
    'Sân vận động 19/8 Nha Trang',
    'Nha Trang, Khánh Hòa', 'stadium', NULL,
    12.250589194133235, 109.19369422726382, 79
),
(
    'Ga Hà Nội',
    'Hoàn Kiếm, Hà Nội', 'traffic', NULL,
    21.024525584524245, 105.84153318223726, 29
),
(
    'Ga Sài Gòn',
    'Quận 3, TP.HCM', 'traffic', NULL,
    10.783980398783381, 106.68068011208564, 41
),
(
    'Ga Đà Nẵng',
    'Thanh Khê, Đà Nẵng', 'traffic', NULL,
    16.074043009438196, 108.21706386050032, 43
),
(
    'Ga Huế',
    'Thuận Hóa, Huế', 'traffic', NULL,
    16.45662635257507, 107.57773670590839, 75
),
(
    'Ga Nha Trang',
    'Nha Trang, Khánh Hòa', 'traffic', NULL,
    12.2486879190878, 109.18471969757748, 79
),
(
    'Ga Vinh',
    'Vinh, Nghệ An', 'traffic', NULL,
    18.68829430279842, 105.66447418203238, 37
),
(
    'Ga Hải Phòng',
    'Hải Phòng', 'traffic', NULL,
    20.856035848707947, 106.68722214965443, 15
),
(
    'Bãi biển Nha Trang',
    'Nha Trang, Khánh Hòa', 'beach', NULL,
    12.238791, 109.196749, 79
),
(
    'Bãi Dài',
    'Cam Lâm, Khánh Hòa', 'beach', NULL,
    12.053741, 109.197007, 79
),
(
    'Bãi biển Mỹ Khê',
    'Sơn Trà, Đà Nẵng', 'beach', NULL,
    16.066885, 108.249937, 43
),
(
    'Bãi biển Non Nước',
    'Ngũ Hành Sơn, Đà Nẵng', 'beach', NULL,
    15.998387, 108.273544, 43
),
(
    'Bãi biển An Bàng',
    'Hội An, Quảng Nam', 'beach', NULL,
    15.903062, 108.342382, 92
),
(
    'Bãi biển Cửa Đại',
    'Hội An, Quảng Nam', 'beach', NULL,
    15.876368, 108.364575, 92
),
(
    'Bãi biển Phú Quốc',
    'Phú Quốc, Kiên Giang', 'beach', NULL,
    10.227039, 103.960739, 68
),
(
    'Bãi biển Nhật Lệ',
    'Đồng Hới, Quảng Bình', 'beach', NULL,
    17.478971, 106.620835, 73
),
(
    'Bãi biển Côn Đảo',
    'Côn Đảo, Bà Rịa-Vũng Tàu', 'beach', NULL,
    8.682553, 106.616159, 72
),
(
    'Bãi biển Trà Cổ',
    'Móng Cái, Quảng Ninh', 'beach', NULL,
    21.521052, 108.045566, 14
),
(
  'Công viên Tao Đàn',
  'Quận 1, TP.HCM', 'park', NULL,
  10.771869, 106.695237, 41
),
(
  'Công viên 30/4',
  'Quận 1, TP.HCM', 'park', NULL,
  10.776211, 106.698524, 41
),
(
  'Công viên Thống Nhất',
  'Hai Bà Trưng, Hà Nội', 'park', NULL,
  21.016913, 105.841138, 29
),
(
  'Công viên Cầu Giấy',
  'Cầu Giấy, Hà Nội', 'park', NULL,
  21.033633, 105.794182, 29
),
(
  'Công viên Bách Thảo',
  'Ba Đình, Hà Nội', 'park', NULL,
  21.043531, 105.832612, 29
),
(
  'Công viên Văn hóa Đầm Sen',
  'Quận 11, TP.HCM', 'park', NULL,
  10.762345, 106.635867, 41
),
(
  'Công viên Biển Đông',
  'Sơn Trà, Đà Nẵng', 'park', NULL,
  16.082414, 108.246243, 43
),
(
  'Công viên APEC',
  'Hải Châu, Đà Nẵng', 'park', NULL,
  16.040649, 108.219942, 43
),
(
  'Công viên Suối Mơ',
  'Long Khánh, Đồng Nai', 'park', NULL,
  11.325803, 107.295418, 60
),
(
  'Công viên VinWonders',
  'Phú Quốc, Kiên Giang', 'park', NULL,
  10.377183, 103.919659, 68
);
