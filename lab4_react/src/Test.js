import './App.css';
import { register } from "./api";
import { useState } from 'react';

function Test() {
    // Состояние для пользователя и ответа от API
    const [user, setUser] = useState({
        name: '',
        password: ''
    });

    const [response, setResponse] = useState('');

    // Обработчик для отправки данных
    const send = async (e) => {
        e.preventDefault(); // предотвращаем перезагрузку страницы

        const result = await register(user); // получаем ответ от API
        setResponse(result); // сохраняем результат в состояние
        console.log(result); // выводим в консоль
    };

    // Обработчик для обновления состояния пользователя
    const handleChange = (e) => {
        const { name, value } = e.target;
        setUser((prevUser) => ({
            ...prevUser,
            [name]: value
        }));
    };

    return (
        <div className="App">
            <header>
                privet
            </header>

            <main>
                <form onSubmit={send}>
                    <input
                        type="text"
                        name="name"
                        value={user.name}
                        onChange={handleChange}
                    />
                    <input
                        type="password"
                        name="password"
                        value={user.password}
                        onChange={handleChange}
                    />
                    <button type="submit">Submit</button>
                </form>

                {/* Выводим ответ */}
                <div>
                    <h2>Response:</h2>
                    <pre>{JSON.stringify(response, null, 2)}</pre>
                </div>
            </main>
        </div>
    );
}

export default Test;
