import { configureStore } from '@reduxjs/toolkit';
import formSlice from "../features/form/formSlice"; // управляет состоянием формы
import tableSlice from "../features/rows/tableSlice"; // ранит данные для таблицы
import authSlice from "../features/auth/authSlice"; // отвечает за аутентификацию пользователя


export const store = configureStore({ ////создаем редукс хранилице
    reducer: {
        form: formSlice,
        auth: authSlice,
        table: tableSlice,
    },
});
