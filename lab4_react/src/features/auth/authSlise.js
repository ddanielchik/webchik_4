import {createSlice} from "@reduxjs/toolkit";
//задаю начальное состояние для слайса(модуля состояния)
const initialState ={
    login: undefined, // на старте пока такие
    password: undefined, // значения не заданы
    repeatedPassword: undefined,
    isLogin: true,
    errorMessage: undefined
}

// createSlice создает слайс  состояния
const authSlice = createSlice(
    {
        name: "authSlice",
        initialState,
        reducers:{
            setLogin: (state, action)=> {
                state.login=action.payload
            },
            setPassword: (state, action)=> {
                state.password=action.payload
            },
            setRepeatedPassword: (state, action)=>{
                state.repeatedPassword=action.payload
            },
            setToLogin:(state, action)=>{
                state.isLogin=action.payload
            },
            setErrorMessage:(state, action)=>{
                state.errorMessage=action.payload
            }


        }
    }
)