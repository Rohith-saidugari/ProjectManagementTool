import React from "react";
import "./App.css";
import Dashboard from "./Components/Dashboard";
import Header from "./Components/Layout/Header";

function App() {
  return (
    <div className="App">
      <Header></Header>
      <Dashboard></Dashboard>
    </div>
  );
}

export default App;
