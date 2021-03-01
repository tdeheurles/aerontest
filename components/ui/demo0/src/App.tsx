import React, { useEffect } from 'react';
import { webSocket } from "rxjs/webSocket";
import logo from './logo.svg';
import './App.css';

function App() {
  const subject = webSocket("ws:localhost:8081")

  useEffect(() =>{
    subject.subscribe(
        msg => console.log('message received: ' + msg),
        err => console.log(err),
        () => console.log("complete")
    );

    subject.next("Hello, I'm `browser` and you ?");
  });

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
