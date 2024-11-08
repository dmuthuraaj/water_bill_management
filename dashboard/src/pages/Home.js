import React from "react";
import "./Home.css";

function Home() {
  return (
    <div className="home-background">
      <div className="container">
        <h1 style={{color: "#FFFFFF"}}>Welcome to Water bill Management Server</h1>
        <p style={{color: "#FFFFFF"}}>
          Use the navigation bar to explore devices, users, and payment status.
        </p>
      </div>
    </div>
  );
}

export default Home;
