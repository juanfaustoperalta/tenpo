INSERT INTO public.roles (id, name)
VALUES (1, 'USER_ROLE');
INSERT INTO public.users (id, password, username)
VALUES (2, '$2a$10$6A1EVQ/cLg2zVc9Vq6vchulUlPPBa/YRcXqd1WMaiZNfW6mpZ69dK', 'test');
INSERT INTO public.user_roles(user_id, role_id)
VALUES (2, 1);