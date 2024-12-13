--
INSERT INTO hotel (
    hotel_name, hotel_email, phone_number, address, latitude, longitude, description,
    check_in_time, check_out_time, website, rating, total_rating, review_count,
    city_id, type_of_hotel, cancellation_policy, deposit_required, pets_allowed,
    smoking_policy, extra_bed_policy
) VALUES
(
    'Furama Resort Danang',
    'reservations@furamadanang.com', '02363847888',
    '105 Võ Nguyên Giáp, Khuê Mỹ, Ngũ Hành Sơn, Đà Nẵng',
    16.040285852458883, 108.25194785986699,
    'Khu nghỉ dưỡng cao cấp nằm sát bãi biển với các tiện ích như spa, nhà hàng và hồ bơi rộng lớn, mang đến trải nghiệm nghỉ dưỡng đẳng cấp cho du khách với các dịch vụ cao cấp, lý tưởng cho kỳ nghỉ gia đình hoặc các buổi hội nghị.',
    '15:00', '12:00',
    'https://www.furamadanang.com',
    4.6, 340, 200, 43,
    'Resort',
    'Hủy miễn phí trong vòng 72 giờ trước khi nhận phòng.',
    'Yes', 1, 0, 1
),
(
    'Anantara Mui Ne Resort',
    'reservations@anantaramuine.com', '02523740999',
    'Bãi biển Mũi Né, 12A Nguyễn Đình Chiểu, Hàm Tiên, Phan Thiết, Bình Thuận',
    10.941702973684913, 108.19251874259133,
    'Khu nghỉ dưỡng cao cấp với các biệt thự sang trọng, dịch vụ spa và các hoạt động ngoài trời, tọa lạc tại bãi biển Mui Ne nổi tiếng, lý tưởng cho những ai muốn tận hưởng không gian thư giãn giữa thiên nhiên biển xanh.',
    '15:00', '12:00',
    'https://www.anantaramuine.com',
    4.6, 280, 160, 86,
    'Resort',
    'Hủy miễn phí trong vòng 72 giờ trước khi nhận phòng.',
    'Yes', 1, 0, 1
),
(
    'Azerai La Residence',
    'contact@azerailaresidence.com', '02323837474',
    '05 Lê Lợi, TP. Huế, Thừa Thiên-Huế',
    16.45949607318338, 107.58093301384258,
    'Khu nghỉ dưỡng này mang đến một không gian thư giãn với các phòng nghỉ sang trọng, dịch vụ spa cao cấp, và các hoạt động ngoài trời thú vị, là điểm đến lý tưởng cho những ai muốn khám phá văn hóa và lịch sử tại thành phố Huế.',
    '14:00', '12:00',
    'https://www.azerailaresidence.com',
    4.8, 400, 250, 75,
    'Resort',
    'Hủy miễn phí trong vòng 72 giờ trước khi nhận phòng.',
    'Yes', 1, 0, 1
),
(
    'Alma Resort Cam Ranh',
    'contact@alma.com.vn', '02583589999',
    'Nguyễn Tất Thành, Cam Hải Đông, Cam Lâm, Khánh Hòa',
    12.071553597214821, 109.19618416330653,
    'Khu nghỉ dưỡng biển này cung cấp không gian thoải mái với các biệt thự ven biển, khu vui chơi nước cho trẻ em, nhà hàng đẳng cấp và các hoạt động thể thao mạo hiểm, rất phù hợp cho kỳ nghỉ gia đình và nhóm bạn.',
    '14:00', '12:00',
    'https://www.alma.com.vn',
    4.7, 390, 230, 68,
    'Resort',
    'Hủy miễn phí trong vòng 48 giờ trước khi nhận phòng.',
    'Yes', 1, 0, 1
),
(
    'InterContinental Hanoi Westlake',
    'reservations@ichotelsgroup.com', '02462708888',
    '05 Từ Hoa, Quảng An, Tây Hồ, Hà Nội',
    21.058570258344975, 105.83232834959638,
    'Khách sạn 5 sao này nằm ven hồ Tây, cung cấp phòng nghỉ sang trọng và dịch vụ tuyệt vời, là lựa chọn lý tưởng cho các chuyến công tác và kỳ nghỉ dài ngày tại thủ đô Hà Nội.',
    '15:00', '12:00',
    'https://www.intercontinental.com/hanoi',
    4.5, 310, 210, 29,
    'Hotel',
    'Hủy miễn phí trong vòng 72 giờ trước khi nhận phòng.',
    'Yes', 1, 0, 1
),
(
    'Vinpearl Resort & Spa Phú Quốc',
    'reservations@vinpearl.com', '02973559999',
    'Bãi Dài, Gành Dầu, Phú Quốc, Kiên Giang',
    10.331614591281706, 103.85313748861714,
    'Khu nghỉ dưỡng Vinpearl trên đảo Phú Quốc nổi bật với các biệt thự sang trọng, công viên nước và bãi biển riêng, là nơi lý tưởng để thư giãn và vui chơi cùng gia đình hoặc bạn bè.',
    '15:00', '12:00',
    'https://www.vinpearl.com',
    4.6, 280, 190, 68,
    'Resort',
    'Hủy miễn phí trong vòng 72 giờ trước khi nhận phòng.',
    'Yes', 1, 0, 1
),
(
    'Grand Mercure Danang',
    'contact@grandmercuredanang.com', '02363961800',
    'Zone Of The Villas Of Green Island, Lot A1, Hải Châu, Đà Nẵng',
    16.048619479179383, 108.22787573051708,
    'Khách sạn Grand Mercure nằm tại bãi biển trung tâm Đà Nẵng, cung cấp phòng nghỉ hiện đại, nhà hàng chất lượng và khu vực hồ bơi tuyệt vời, là lựa chọn hoàn hảo cho kỳ nghỉ thư giãn hoặc các hội nghị công ty.',
    '14:00', '12:00',
    'https://www.grandmercuredanang.com',
    4.5, 270, 175, 43,
    'Hotel',
    'Hủy miễn phí trong vòng 48 giờ trước khi nhận phòng.',
    'Yes', 1, 0, 1
),
(
    'Muong Thanh Grand Hanoi',
    'info@muongthanh.com', '02439288888',
    'CC2, Bắc Linh Đàm, Hoàng Mai, Hà Nội',
    20.973939342638747, 105.84168662870103,
    'Khách sạn Muong Thanh Grand cung cấp các phòng nghỉ tiện nghi, gần trung tâm thủ đô Hà Nội với các dịch vụ như phòng hội nghị và nhà hàng sang trọng, là lựa chọn lý tưởng cho du khách công tác và nghỉ dưỡng.',
    '14:00', '12:00',
    'https://www.muongthanh.com',
    4.3, 320, 210, 29,
    'Hotel',
    'Hủy miễn phí trong vòng 48 giờ trước khi nhận phòng.',
    'Yes', 1, 0, 1
),
(
    'Sheraton Hanoi Hotel',
    'reservations@sheratonhanoi.com', '02437199000',
    'K5, Nghi Tàm/11 Đ. Xuân Diệu, Quảng An, Tây Hồ, Hà Nội',
    21.06004100901738, 105.83283071257965,
    'Khách sạn Sheraton Hanoi mang đến không gian nghỉ dưỡng sang trọng với các tiện nghi cao cấp, nhà hàng phong cách quốc tế, và dịch vụ thư giãn như spa và phòng tập gym, rất phù hợp cho du khách công tác và nghỉ dưỡng dài ngày.',
    '15:00', '12:00',
    'https://www.sheratonhanoi.com',
    4.6, 360, 220, 29,
    'Hotel',
    'Hủy miễn phí trong vòng 72 giờ trước khi nhận phòng.',
    'Yes', 1, 0, 1
),
(
    'Saigon Prince Hotel',
    'contact@saigonprince.com', '02838220288',
    '63 Nguyễn Huệ, Bến Nghé, Quận 1, TP. Hồ Chí Minh',
    10.773242018888078, 106.70498074629423,
    'Khách sạn Saigon Prince tọa lạc tại vị trí đắc địa trong khu trung tâm thành phố Hồ Chí Minh, cung cấp các phòng nghỉ tiện nghi và dịch vụ chuyên nghiệp, là lựa chọn tuyệt vời cho các chuyến công tác và du lịch.',
    '14:00', '12:00',
    'https://www.saigonprince.com',
    4.4, 300, 180, 41,
    'Hotel',
    'Hủy miễn phí trong vòng 48 giờ trước khi nhận phòng.',
    'Yes', 1, 0, 0
),
(
    'Mui Ne Paradise Resort',
    'contact@muineparadise.com', '02523840011',
    '130D Nguyễn Đình Chiểu, Mũi Né, Phan Thiết, Bình Thuận',
    10.955495793952327, 108.23263694969687,
    'Mui Ne Paradise là khu nghỉ dưỡng ven biển với các biệt thự sang trọng, hồ bơi lớn và nhà hàng phục vụ ẩm thực quốc tế. Khách sạn mang đến một không gian thư giãn tuyệt vời, thích hợp cho du khách yêu thích biển cả và những buổi tối lãng mạn.',
    '15:00', '12:00',
    'https://www.muineparadise.com',
    4.7, 320, 200, 86,
    'Resort',
    'Hủy miễn phí trong vòng 48 giờ trước khi nhận phòng.',
    'Yes', 1, 0, 1
),
(
    'Sheraton Grand Danang Resort & Convention Center',
    'info@sheratongranddanang.com', '02363988888',
    '35 Trường Sa, Ngũ Hành Sơn, Đà Nẵng',
    15.980972139383217, 108.27708845801355,
    'Nằm bên bãi biển Mỹ Khê, resort này cung cấp dịch vụ sang trọng với hồ bơi, spa và nhiều hoạt động thể thao.',
    '15:00', '12:00',
    'https://www.marriott.com/hotels/travel/dngsi-sheraton-grand-danang-resort/',
    4.7, 300, 150, 43,
    'Resort',
    'Hủy miễn phí trong vòng 72 giờ trước khi nhận phòng.',
    'Yes', 1, 0, 0
),
(
    'The Reverie Saigon',
    'reservations@thereveriesaigon.com', '02838236688',
    '22-36 Nguyễn Huệ, Bến Nghé, Quận 1, Hồ Chí Minh',
    10.77378003514775, 106.70469773890648,
    'Khách sạn 5 sao sang trọng tại trung tâm thành phố với thiết kế tinh tế và dịch vụ hoàn hảo.',
    '14:00', '12:00',
    'https://www.thereveriesaigon.com',
    4.9, 500, 200, 41,
    'Hotel',
    'Hủy miễn phí trong vòng 48 giờ trước khi nhận phòng.',
    'No', 1, 0, 0
),
(
    'An Villa Hoi An',
    'info@anvillahoi.com', '02353912888',
    'Võng Nhi, Cẩm Thanh, Hội An, Quảng Nam',
    15.887108104314173, 108.36093606826161,
    'Một homestay yên tĩnh với không gian xanh mát, gần phố cổ và các bãi biển.',
    '14:00', '12:00',
    'https://www.anvillahoi.com',
    4.8, 100, 60, 92,
    'Homestay',
    'Hủy miễn phí trong vòng 48 giờ trước khi nhận phòng.',
    'Yes', 1, 0, 0
);

INSERT INTO room (
    name, type, room_size, max_guests, type_bed_1, no_bed_1, type_bed_2, no_bed_2,
    price, available_rooms, hotel_id
) VALUES
('Ocean View Deluxe', 'Deluxe', 50, 3, 'King', 1, NULL, 0, 4800000, 10, 1),
('Family Suite', 'Suite', 80, 5, 'Queen', 2, 'Single', 1, 8400000, 5, 1),
('Standard Room', 'Standard', 30, 2, 'Double', 1, NULL, 0, 2880000, 15, 1),
('Sea View Room', 'Deluxe', 45, 3, 'King', 1, NULL, 0, 4320000, 8, 2),
('Pool Villa', 'Villa', 120, 6, 'Queen', 2, 'Single', 2, 9600000, 4, 2),
('Budget Room', 'Standard', 25, 2, 'Single', 2, NULL, 0, 2400000, 12, 2),
('City View Executive', 'Executive', 60, 4, 'King', 1, 'Single', 1, 5760000, 6, 3),
('Business Suite', 'Suite', 70, 5, 'Queen', 2, 'Single', 1, 6720000, 4, 3),
('Compact Studio', 'Studio', 25, 2, 'Double', 1, NULL, 0, 2160000, 20, 3),
('Beachfront Villa', 'Villa', 100, 4, 'King', 1, NULL, 0, 9600000, 6, 4),
('Garden View Suite', 'Suite', 60, 3, 'Queen', 1, NULL, 0, 5280000, 8, 4),
('Standard Room', 'Standard', 30, 2, 'Double', 1, NULL, 0, 2880000, 12, 4),
('Deluxe River View', 'Deluxe', 45, 3, 'Queen', 1, NULL, 0, 5520000, 8, 5),
('Junior Suite', 'Suite', 70, 4, 'King', 1, 'Single', 1, 7920000, 5, 5),
('Classic Room', 'Standard', 25, 2, 'Double', 1, NULL, 0, 2160000, 14, 5),
('Ocean Front Villa', 'Villa', 120, 6, 'Queen', 2, 'Single', 2, 11520000, 4, 6),
('Garden Suite', 'Suite', 60, 4, 'King', 1, 'Single', 1, 6240000, 6, 6),
('Economy Room', 'Standard', 25, 2, 'Single', 2, NULL, 0, 2160000, 10, 6),
('Lake View Room', 'Deluxe', 50, 3, 'King', 1, NULL, 0, 5280000, 7, 7),
('Presidential Suite', 'Suite', 90, 6, 'Queen', 2, 'Single', 1, 14400000, 3, 7),
('Standard Room', 'Standard', 30, 2, 'Double', 1, NULL, 0, 2880000, 15, 7),
('Beachfront Villa', 'Villa', 120, 6, 'King', 2, 'Single', 2, 12000000, 4, 8),
('Ocean View Suite', 'Suite', 70, 4, 'Queen', 1, 'Single', 1, 7200000, 6, 8),
('Cozy Room', 'Standard', 30, 2, 'Double', 1, NULL, 0, 3600000, 10, 8),
('Executive Room', 'Executive', 50, 3, 'King', 1, NULL, 0, 4800000, 7, 9),
('Luxury Suite', 'Suite', 75, 5, 'Queen', 2, 'Single', 1, 8400000, 4, 9),
('Budget Room', 'Standard', 25, 2, 'Single', 2, NULL, 0, 2400000, 10, 9),
('Deluxe Room', 'Deluxe', 50, 3, 'King', 1, NULL, 0, 5520000, 6, 10),
('Business Suite', 'Suite', 70, 5, 'Queen', 2, 'Single', 1, 7920000, 3, 10),
('Compact Room', 'Standard', 25, 2, 'Single', 2, NULL, 0, 2160000, 15, 10),
('Executive Suite', 'Suite', 65, 4, 'King', 1, 'Single', 1, 8400000, 5, 11),
('Deluxe Room', 'Deluxe', 50, 3, 'Queen', 1, NULL, 0, 5520000, 8, 11),
('Standard Room', 'Standard', 30, 2, 'Double', 1, NULL, 0, 2880000, 12, 11),
('Emerald Bay Suite', 'Suite', 75, 5, 'King', 1, 'Single', 1, 10800000, 4, 12),
('Oceanfront Villa', 'Villa', 120, 6, 'Queen', 2, 'Single', 2, 13200000, 3, 12),
('Deluxe Room', 'Deluxe', 40, 3, 'Queen', 1, NULL, 0, 5280000, 10, 12),
('Panoramic Suite', 'Suite', 80, 5, 'King', 1, 'Single', 1, 14400000, 4, 13),
('City View Deluxe', 'Deluxe', 45, 3, 'Queen', 1, NULL, 0, 5760000, 7, 13),
('Economy Room', 'Standard', 25, 2, 'Single', 2, NULL, 0, 2400000, 15, 13),
('Luxury Villa', 'Villa', 130, 6, 'King', 2, 'Single', 2, 18000000, 3, 14),
('Beachfront Deluxe', 'Deluxe', 50, 4, 'Queen', 1, NULL, 0, 7200000, 6, 14),
('Cozy Room', 'Standard', 30, 2, 'Double', 1, NULL, 0, 3600000, 12, 14);

INSERT INTO amenity (name) VALUES
('Wi-Fi miễn phí'), ('Bể bơi'), ('Dịch vụ giặt là'), ('Trung tâm thể dục'),
('Bãi đỗ xe miễn phí'), ('Nhà hàng'), ('Dịch vụ phòng'), ('Spa và chăm sóc sức khỏe'),
('Quầy bar'), ('Phòng hội nghị'), ('Đưa đón sân bay'), ('Phòng không hút thuốc'),
('Tiện nghi cho người khuyết tật'), ('Sân chơi trẻ em'), ('Máy điều hòa'), ('TV'),
('Tủ lạnh mini'), ('Két sắt'), ('Máy pha cà phê'), ('Bàn làm việc'), ('Ban công riêng'),
('Bồn tắm'), ('Đồ vệ sinh cá nhân miễn phí'), ('Máy sấy tóc'), ('Dép đi trong phòng'),
('Điện thoại nội bộ'), ('Bộ ấm chén'), ('Dịch vụ báo thức'), ('Wifi trong phòng');

INSERT INTO hotel_image (image_url, hotel_id)
VALUES
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-01.jpg?alt=media&token=5dbb6493-370b-4040-b27d-1839319fd14b", 1),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-02.jpg?alt=media&token=100aafb6-52c3-44cd-9f3e-d6450a9ef133", 1),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-03.jpg?alt=media&token=02d05419-1248-4487-9885-c09ccc7026e3", 1),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-01.jpg?alt=media&token=5dbb6493-370b-4040-b27d-1839319fd14b", 2),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-02.jpg?alt=media&token=100aafb6-52c3-44cd-9f3e-d6450a9ef133", 2),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-03.jpg?alt=media&token=02d05419-1248-4487-9885-c09ccc7026e3", 2),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-01.jpg?alt=media&token=5dbb6493-370b-4040-b27d-1839319fd14b", 3),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-02.jpg?alt=media&token=100aafb6-52c3-44cd-9f3e-d6450a9ef133", 3),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-03.jpg?alt=media&token=02d05419-1248-4487-9885-c09ccc7026e3", 3),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-01.jpg?alt=media&token=5dbb6493-370b-4040-b27d-1839319fd14b", 4),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-02.jpg?alt=media&token=100aafb6-52c3-44cd-9f3e-d6450a9ef133", 4),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-03.jpg?alt=media&token=02d05419-1248-4487-9885-c09ccc7026e3", 4),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-01.jpg?alt=media&token=5dbb6493-370b-4040-b27d-1839319fd14b", 5),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-02.jpg?alt=media&token=100aafb6-52c3-44cd-9f3e-d6450a9ef133", 5),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-03.jpg?alt=media&token=02d05419-1248-4487-9885-c09ccc7026e3", 5),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-01.jpg?alt=media&token=5dbb6493-370b-4040-b27d-1839319fd14b", 6),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-02.jpg?alt=media&token=100aafb6-52c3-44cd-9f3e-d6450a9ef133", 6),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-03.jpg?alt=media&token=02d05419-1248-4487-9885-c09ccc7026e3", 6),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-01.jpg?alt=media&token=5dbb6493-370b-4040-b27d-1839319fd14b", 7),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-02.jpg?alt=media&token=100aafb6-52c3-44cd-9f3e-d6450a9ef133", 7),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-03.jpg?alt=media&token=02d05419-1248-4487-9885-c09ccc7026e3", 7),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-01.jpg?alt=media&token=5dbb6493-370b-4040-b27d-1839319fd14b", 8),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-02.jpg?alt=media&token=100aafb6-52c3-44cd-9f3e-d6450a9ef133", 8),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-03.jpg?alt=media&token=02d05419-1248-4487-9885-c09ccc7026e3", 8),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-01.jpg?alt=media&token=5dbb6493-370b-4040-b27d-1839319fd14b", 9),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-02.jpg?alt=media&token=100aafb6-52c3-44cd-9f3e-d6450a9ef133", 9),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-03.jpg?alt=media&token=02d05419-1248-4487-9885-c09ccc7026e3", 9),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-01.jpg?alt=media&token=5dbb6493-370b-4040-b27d-1839319fd14b", 10),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-02.jpg?alt=media&token=100aafb6-52c3-44cd-9f3e-d6450a9ef133", 10),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-03.jpg?alt=media&token=02d05419-1248-4487-9885-c09ccc7026e3", 10),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-01.jpg?alt=media&token=5dbb6493-370b-4040-b27d-1839319fd14b", 11),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-02.jpg?alt=media&token=100aafb6-52c3-44cd-9f3e-d6450a9ef133", 11),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-03.jpg?alt=media&token=02d05419-1248-4487-9885-c09ccc7026e3", 11),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-01.jpg?alt=media&token=5dbb6493-370b-4040-b27d-1839319fd14b", 12),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-02.jpg?alt=media&token=100aafb6-52c3-44cd-9f3e-d6450a9ef133", 12),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-03.jpg?alt=media&token=02d05419-1248-4487-9885-c09ccc7026e3", 12),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-01.jpg?alt=media&token=5dbb6493-370b-4040-b27d-1839319fd14b", 13),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-02.jpg?alt=media&token=100aafb6-52c3-44cd-9f3e-d6450a9ef133", 13),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-03.jpg?alt=media&token=02d05419-1248-4487-9885-c09ccc7026e3", 13),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-01.jpg?alt=media&token=5dbb6493-370b-4040-b27d-1839319fd14b", 14),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-02.jpg?alt=media&token=100aafb6-52c3-44cd-9f3e-d6450a9ef133", 14),
("https://firebasestorage.googleapis.com/v0/b/travel-web-7b510.appspot.com/o/hotel%2Fimage-03.jpg?alt=media&token=02d05419-1248-4487-9885-c09ccc7026e3", 14);

INSERT INTO amenity_for_hotel (amenity_id, hotel_id) VALUES
(1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1),
(1, 2), (3, 2), (4, 2), (5, 2), (6, 2), (7, 2), (8, 2), (9, 2),
(2, 3), (3, 3), (4, 3), (5, 3), (6, 3), (7, 3), (8, 3), (9, 3),
(1, 4), (2, 4), (5, 4), (6, 4), (7, 4), (8, 4), (10, 4), (11, 4),
(1, 5), (3, 5), (4, 5), (5, 5), (6, 5), (7, 5), (12, 5), (13, 5),
(2, 6), (3, 6), (5, 6), (7, 6), (8, 6), (9, 6), (10, 6), (14, 6),
(1, 7), (3, 7), (4, 7), (5, 7), (6, 7), (7, 7), (15, 7), (16, 7),
(1, 8), (2, 8), (3, 8), (4, 8), (6, 8), (7, 8), (9, 8), (17, 8),
(5, 9), (6, 9), (7, 9), (8, 9), (11, 9), (12, 9), (13, 9), (18, 9),
(1, 10), (2, 10), (6, 10), (7, 10), (8, 10), (9, 10), (14, 10), (19, 10),
(1, 11), (2, 11), (3, 11), (4, 11), (5, 11), (6, 11), (7, 11), (20, 11),
(1, 12), (3, 12), (5, 12), (6, 12), (7, 12), (9, 12), (10, 12), (21, 12),
(2, 13), (3, 13), (4, 13), (6, 13), (7, 13), (8, 13), (11, 13), (22, 13),
(1, 14), (3, 14), (5, 14), (7, 14), (8, 14), (9, 14), (13, 14), (23, 14);

INSERT INTO amenity_for_room (amenity_id, room_id)
VALUES
(25, 1),
(26, 1),
(27, 1);

INSERT INTO nearby_attraction (distance, hotel_id, attraction_id)
VALUES
(4, 1, 1);









