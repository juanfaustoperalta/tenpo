INSERT INTO public.transaction_history (id, cause, created_at, exception, status, transaction_name)
VALUES (1, null, '2021-02-06 21:07:36.645246', null, 'SUCCESS', 'LOGIN');
INSERT INTO public.transaction_history (id, cause, created_at, exception, status, transaction_name)
VALUES (2, 'This username test has really exist', '2021-02-06 21:08:18.140772',
        'com.tenpo.app.exceptions.UserHasAlreadyExistException', 'FAILED', 'SIGNUP');
INSERT INTO public.transaction_history (id, cause, created_at, exception, status, transaction_name)
VALUES (3, null, '2021-02-06 21:08:46.667390', null, 'SUCCESS', 'HISTORY');
INSERT INTO public.transaction_history (id, cause, created_at, exception, status, transaction_name)
VALUES (4, 'Bad credentials', '2021-02-06 21:07:36.645246', 'BAD_REQUEST', 'FAILED', 'LOGIN');
