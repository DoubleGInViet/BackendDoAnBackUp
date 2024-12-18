CREATE TABLE users (
    id INT auto_increment primary key,
    name varchar(100),
    first_name varchar(100),
    last_name varchar(100),
    phone_number varchar(20) unique,
    avatar varchar(255),
    email varchar(255) not null unique,
    country varchar(255),
    address varchar(255),
    date_of_birth DATE,
    active TINYINT(1),
    password varchar(100) not null,
    role varchar(100)
);

CREATE TABLE city (
    id INT AUTO_INCREMENT PRIMARY KEY,
    city_name NVARCHAR(100) NOT NULL,
    type NVARCHAR(100),
    parent_id INT
);

ALTER TABLE city ADD FULLTEXT(city_name);

CREATE TABLE hotel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hotel_name NVARCHAR(100) NOT NULL,
    hotel_email NVARCHAR(100),
    phone_number NVARCHAR(20),
    address NVARCHAR(255),
    longitude FLOAT,
    latitude FLOAT,
    description TEXT,
    check_in_time TIME,
    check_out_time TIME,
    website NVARCHAR(255),
    rating FLOAT,
    total_rating INT,
    review_count INT,
    city_id INT,
    type_of_hotel NVARCHAR(255),
    cancellation_policy NVARCHAR(255),
    deposit_required NVARCHAR(255),
    pets_allowed TINYINT(1),
    smoking_policy TINYINT(1),
    extra_bed_policy TINYINT(1),
    CONSTRAINT fk_hotel_city FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE TABLE hotel_image (
    id INT AUTO_INCREMENT PRIMARY KEY,
    image_url NVARCHAR(255),
    hotel_id INT,
    CONSTRAINT fk_hotel_image_hotel FOREIGN KEY (hotel_id) REFERENCES hotel(id)
);


CREATE TABLE room (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(255),
    type NVARCHAR(255),
    room_size INT,
    max_guests INT,
    type_bed_1 NVARCHAR(255),
    no_bed_1 INT,
    type_bed_2 NVARCHAR(255),
    no_bed_2 INT,
    price INT,
    available_rooms INT,
    hotel_id INT,
    CONSTRAINT fk_room_hotel FOREIGN KEY (hotel_id) references hotel(id)
);

create table amenity (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(255)
);

create table amenity_for_room (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amenity_id INT,
    room_id INT,
    CONSTRAINT fk_amenity_for_room_amenity FOREIGN KEY (amenity_id) REFERENCES amenity(id),
    CONSTRAINT fk_amenity_for_room_room FOREIGN KEY (room_id) REFERENCES room(id)
);


CREATE TABLE booking_room (
    id NVARCHAR(8) PRIMARY KEY,
    booking_date DATE,
    adults INT,
    children INT,
    check_in_date DATE,
    check_out_date DATE,
    total_price INT,
    total_rooms INT,
    customer_full_name NVARCHAR(255),
    customer_email NVARCHAR(255),
    customer_phone_number NVARCHAR(255),
    customer_country NVARCHAR(255),
    special_request NVARCHAR(255),
    arrival_time NVARCHAR(255),
    status NVARCHAR(255),
    user_id INT,
    CONSTRAINT fk_booking_room_users foreign key (user_id) references users(id)
);

CREATE TABLE booked_room (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount INT,
    price_per INT,
    room_id INT,
    booking_room_id NVARCHAR(20),
    CONSTRAINT fk_booked_room_room foreign key (room_id) references room(id),
    CONSTRAINT fk_booked_room_booking foreign key (booking_room_id) references booking_room(id)
);

CREATE TABLE attraction (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(255),
    latitude FLOAT,
    longitude FLOAT,
    address NVARCHAR(255),
    type NVARCHAR(255),
    image TEXT,
    city_id INT,
    CONSTRAINT fk_attraction_city foreign key (city_id) references city(id)
);


CREATE TABLE rental_facility (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    phone_number NVARCHAR(20),
    email NVARCHAR(100),
    description TEXT,
    address TEXT,
    rating FLOAT,
    total_rating INT,
    review_count INT
);

CREATE TABLE office (
    id INT AUTO_INCREMENT PRIMARY KEY,
    attraction_id INT,
    rental_facility_id INT,
    CONSTRAINT fk_office_attraction foreign key (attraction_id) references attraction(id),
    CONSTRAINT fk_office_rental foreign key (rental_facility_id) references rental_facility(id)
);

CREATE TABLE vehicle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(255),
    brand NVARCHAR(255),
    vehicle_type NVARCHAR(255),
    fuel NVARCHAR(255),
    engine NVARCHAR(255),
    year INT
);


CREATE TABLE motor (
    motor_id INT PRIMARY KEY,
    capacity NVARCHAR(255),
    FOREIGN KEY (motor_id) references vehicle(id)
);


CREATE TABLE car (
    car_id INT PRIMARY KEY,
    transmission NVARCHAR(255),
    baggage_capacity NVARCHAR(255),
    seats INT,
    FOREIGN KEY (car_id) references vehicle(id)
);

CREATE TABLE rental_vehicle (
    id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT NOT NULL,
    rental_facility_id INT NOT NULL,
    price INT NOT NULL,
    stake INT,
    available_vehicle INT,
    CONSTRAINT fk_vrf_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle(id),
    CONSTRAINT fk_vrf_rental FOREIGN KEY (rental_facility_id) REFERENCES rental_facility(id)
);

CREATE TABLE booking_vehicle (
    id NVARCHAR(8) PRIMARY KEY,
    booking_date DATE,
    start_date DATE,
    start_time TIME,
    start_address NVARCHAR(255),
    return_date DATE,
    return_time TIME,
    return_address NVARCHAR(255),
    customer_full_name NVARCHAR(255),
    customer_email NVARCHAR(255),
    customer_phone_number NVARCHAR(255),
    customer_country NVARCHAR(255),
    total_service_price INT,
    total_price INT,
    user_id INT,
    rental_vehicle_id INT,
    CONSTRAINT fk_booking_motor_users foreign key (user_id) references users(id),
    CONSTRAINT fk_booking_motor_rental_vehicle foreign key (rental_vehicle_id) references rental_vehicle(id)
);

CREATE TABLE addition_driver (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title INT,
    full_name NVARCHAR(255),
    phone NVARCHAR(20),
    booking_vehicle_id NVARCHAR(8),
    CONSTRAINT fk_driver_booking foreign key (booking_vehicle_id) references booking_vehicle(id)
);

create table accessory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(255),
    price INT,
    type NVARCHAR(255),
    max_value INT
);

CREATE TABLE accessory_booking (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount int,
    price_per INT,
    accessory_id INT,
    booking_vehicle_id NVARCHAR(8),
    CONSTRAINT fk_service_booking_service foreign key (accessory_id) references accessory(id),
    CONSTRAINT fk_service_booking_booking foreign key (booking_vehicle_id) references booking_vehicle(id)
);

CREATE TABLE tour (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(255),
    address NVARCHAR(255),
    start_time TIME,
    end_time TIME,
    description TEXT,
    attraction_id INT,
    CONSTRAINT fk_tour_city foreign key (attraction_id) references attraction(id)
);

CREATE TABLE tour_image (
    id INT AUTO_INCREMENT PRIMARY KEY,
    image_url NVARCHAR(255),
    tour_id INT,
    CONSTRAINT fk_tour_image_tour foreign key (tour_id) references tour(id)
);


CREATE TABLE ticket_class (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(255),
    adult_price INT,
    children_price INT,
    available_ticket INT,
    description TEXT,
    tour_id INT,
    CONSTRAINT fk_ticket_class_tour foreign key (tour_id) references tour(id)
);

CREATE TABLE profit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(255)
);

CREATE TABLE ticket_profit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    profit_id INT,
    ticket_class_id INT,
    CONSTRAINT fk_ticket_profit_profit foreign key (profit_id) references profit(id),
    CONSTRAINT fk_ticket_profit_ticket foreign key (ticket_class_id) references ticket_class(id)
);

CREATE TABLE booking_ticket (
    id INT AUTO_INCREMENT PRIMARY KEY,
    booking_date DATE,
    tour_date DATE,
    number_adult_ticket INT,
    number_children_ticket INT,
    total_price INT,
    ticket_class_id INT,
    user_id INT,
    CONSTRAINT fk_booking_ticket_ticket foreign key (ticket_class_id) references ticket_class(id),
    CONSTRAINT fk_booking_ticket_user foreign key (user_id) references users(id)
);

CREATE TABLE review (
    id INT AUTO_INCREMENT PRIMARY key,
    rating INT,
    comment TEXT,
    review_date DATETIME,
    hotel_id INT,
    user_id INT,
    CONSTRAINT fk_evaluation_hotel foreign key (hotel_id) references hotel(id),
    CONSTRAINT fk_evaluation_user foreign key (user_id) references users(id)
);

CREATE TABLE nearby_attraction (
    id INT AUTO_INCREMENT PRIMARY KEY,
    distance FLOAT,
    hotel_id INT,
    attraction_id INT,
    CONSTRAINT fk_nearby_hotel foreign key (hotel_id) references hotel(id),
    CONSTRAINT fk_nearby_attraction foreign key (attraction_id) references attraction(id)
);

CREATE TABLE amenity_for_hotel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amenity_id INT,
    hotel_id INT,
    CONSTRAINT fk_amenity_for_hotel_amenity FOREIGN KEY (amenity_id) REFERENCES amenity(id),
    CONSTRAINT fk_amenity_for_hotel_hotel FOREIGN KEY (hotel_id) REFERENCES hotel(id)
);

CREATE TABLE notification (
    id INT PRIMARY KEY,
    content TEXT,
    type NVARCHAR(10)
);

CREATE TABLE review_rental (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rating INT,
        comment TEXT,
        review_date DATETIME,
        rental_id INT,
        user_id INT,
        CONSTRAINT fk_review_rental foreign key (rental_id) references rental_facility(id),
        CONSTRAINT fk_review_rental_user foreign key (user_id) references users(id)
);

CREATE TABLE review_tour (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rating INT,
        comment TEXT,
        review_date DATETIME,
        tour_id INT,
        user_id INT,
        CONSTRAINT fk_review_tour foreign key (tour_id) references tour(id),
        CONSTRAINT fk_review_tour_user foreign key (user_id) references users(id)
);


-----------------------------------------------------------------------------------------

-- Tạo Trigger cho bảng hotel
DELIMITER //

CREATE TRIGGER after_hotel_insert
AFTER INSERT ON hotel
FOR EACH ROW
BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE a_id INT;
    DECLARE a_lat FLOAT;
    DECLARE a_lon FLOAT;
    DECLARE a_type NVARCHAR(255);
    DECLARE cur CURSOR FOR
        SELECT id, latitude, longitude, type FROM attraction WHERE city_id = NEW.city_id;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO a_id, a_lat, a_lon, a_type;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- Kiểm tra type của attraction phải khác "office"
        IF a_type <> 'office' THEN
            SET @distance = 6371 * ACOS(
                COS(RADIANS(NEW.latitude)) *
                COS(RADIANS(a_lat)) *
                COS(RADIANS(a_lon) - RADIANS(NEW.longitude)) +
                SIN(RADIANS(NEW.latitude)) *
                SIN(RADIANS(a_lat))
            );

            IF @distance < 10 THEN
                INSERT INTO nearby_attraction (distance, hotel_id, attraction_id)
                VALUES (ROUND(@distance, 2), NEW.id, a_id);
            END IF;
        END IF;
    END LOOP;

    CLOSE cur;
END;
//

DELIMITER ;



-- Tạo Trigger cho bảng attraction
DELIMITER //

CREATE TRIGGER after_attraction_insert
AFTER INSERT ON attraction
FOR EACH ROW
BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE h_id INT;
    DECLARE h_lat FLOAT;
    DECLARE h_lon FLOAT;
    DECLARE cur CURSOR FOR
        SELECT id, latitude, longitude FROM hotel WHERE city_id = NEW.city_id;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO h_id, h_lat, h_lon;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- Kiểm tra type của attraction phải khác "office"
        IF NEW.type <> 'office' THEN
            SET @distance = 6371 * ACOS(
                COS(RADIANS(NEW.latitude)) *
                COS(RADIANS(h_lat)) *
                COS(RADIANS(h_lon) - RADIANS(NEW.longitude)) +
                SIN(RADIANS(NEW.latitude)) *
                SIN(RADIANS(h_lat))
            );

            IF @distance < 10 THEN
                INSERT INTO nearby_attraction (distance, hotel_id, attraction_id)
                VALUES (ROUND(@distance, 2), h_id, NEW.id);
            END IF;
        END IF;
    END LOOP;

    CLOSE cur;
END;
//

DELIMITER ;











