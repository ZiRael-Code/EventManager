truncate table user;
# truncate table attendees;
# truncate table event_maker;
truncate table event;

insert  into user (login_status, id, email, name, password) values
    (true, 2,  'email1@gmal.com',  'name', 'Israelites12!');
#
# INSERT INTO event_maker(id, user_id) values
#                   (1, 2);
#
# INSERT INTO attendees(id, user_id) values
#                   (1, 2);

INSERT INTO event (category, date_created, event_date, available_attendees, available_ticket, event_maker_id_id,  id, description, name)
VALUES (0, '2024-4-12', '2024-4-10',100, 100, 2,  1, 'j', 'Game boi' );
#
