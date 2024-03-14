import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { useEffect } from 'react';


function App() {
    // const [count, setCount] = useState(0)

    // return (
    //   <>
    //     <div>
    //       <a href="https://vitejs.dev" target="_blank">
    //         <img src={viteLogo} className="logo" alt="Vite logo" />
    //       </a>
    //       <a href="https://react.dev" target="_blank">
    //         <img src={reactLogo} className="logo react" alt="React logo" />
    //       </a>
    //     </div>
    //     <h1>Vite + React</h1>
    //     <div className="card">
    //       <button onClick={() => setCount((count) => count + 1)}>
    //         count is {count}
    //       </button>
    //       <p>
    //         Edit <code>src/App.jsx</code> and save to test HMR
    //       </p>
    //     </div>
    //     <p className="read-the-docs">
    //       Click on the Vite and React logos to learn more
    //     </p>
    //   </>
    // )

    const [data, setData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch('https://gnews.io/api/v4/top-headlines?category=general&lang=en&country=us&max=10&apikey=7ec775bebc7bd5c662f87508cd69b769');
                const jsonData = await response.json();
                setData(jsonData.articles);
                console.log(jsonData.articles);
            } catch (error) {
                console.log(error);
            }
        };

        fetchData();
    }, []);

    return (
        <div className="list-view">
            <h1>News List</h1>
            <ul>
                {data.map((item, index) => (
                    <li key={index} className="list-item">
                        <div className="item-content">
                            <img src={item.image} alt={item.title} className="item-image" />
                            <div className="item-details">
                                <h2 className="item-title">{item.title}</h2>
                                <p className="item-description">{item.description}</p>
                                <p className="item-published">{item.publishedAt}</p>
                                <p className="item-source">{item.source.name}</p>
                            </div>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );

}

export default App
