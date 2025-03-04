import React from 'react';
import ReactDOM from 'react-dom/client';
import { Provider } from 'react-redux';
import './index.css';
import Test from "./Test";

import { createRoot } from 'react-dom/client';
import { store } from './app/store';
import reportWebVitals from './reportWebVitals';
import './index.css';
import { Route, BrowserRouter, Routes } from 'react-router-dom';
import PrivateRoute from "./components/PrivateRoute";
import LoginCard from "./components/LoginCard";
import {goToLogin, goToMain, tryToGenNewToken} from "./utils";
import App from "./App";

//создаем корневой контейнер для реакт
const container = document.getElementById('root');
const root = createRoot(container);
root.render(
    <BrowserRouter>
        {/*передает redux store в дочерние компоненты*/}
        <Provider store={store}>
            <Routes>
                <Route path='/login' element={<LoginCard/>}/>
                <Route path='/' element={<PrivateRoute element={<App />} accessFunction={tryToGenNewToken} onFail={() => goToLogin()}/>} />
            </Routes>

        </Provider>
    </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
