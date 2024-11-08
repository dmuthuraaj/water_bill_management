import React, { useState, useEffect } from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Link,
  Navigate,
  useLocation,
} from "react-router-dom";
import Devices from "./pages/Devices";
import Users from "./pages/Users";
import DeviceDetails from "./pages/DeviceDetails";
import UserDetails from "./pages/UserDetails";
import Login from "./pages/Login";
import Home from "./pages/Home";
import AddDevice from "./pages/AddDevice";
import AddUser from "./pages/AddUser";
import Clients from "./pages/Clients";
import ClientDetails from "./pages/ClientDetails";
import AddClient from "./pages/AddClient";
import UploadCsv from "./pages/UploadCsv";
import BillDetails from "./pages/BillDetails";
import AddBill from "./pages/AddBill";
import Bills from "./pages/Bills";
import EditDevice from "./pages/EditDevice";
import EditUser from "./pages/EditUser";
import EditClient from "./pages/EditClient";
import EditBill from "./pages/EditBill";
import ReportDetails from "./pages/ReportDetails";

function MainLayout({ isAuthenticated, logout }) {
  const location = useLocation();
  const showNavbar = location.pathname !== "/login";

  return (
    <>
      {showNavbar && (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <Link className="navbar-brand" to="/">
            LOGO
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav mr-auto">
              <li className="nav-item">
                <Link className="nav-link" to="/devices">
                  Devices
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/clients">
                  Clients
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/bills">
                  Bills
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/users">
                  Users
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/report">
                  Report
                </Link>
              </li>
            </ul>
            <ul className="navbar-nav ms-auto">
              {isAuthenticated ? (
                <li className="nav-item">
                  <button className="nav-link btn btn-link" onClick={logout}>
                    Logout
                  </button>
                </li>
              ) : (
                <li className="nav-item">
                  <Link className="nav-link" to="/login">
                    Login
                  </Link>
                </li>
              )}
            </ul>
          </div>
        </nav>
      )}
    </>
  );
}

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const login = (username, password) => {
    if (username === "admin" && password === "admin") {
      setIsAuthenticated(true);
      localStorage.setItem("auth", "true");
    } else {
      setIsAuthenticated(false);
      localStorage.setItem("auth", "false");
      alert("Invalid credentials");
    }
  };

  const logout = () => {
    setIsAuthenticated(false);
    localStorage.removeItem("auth");
  };

  useEffect(() => {
    const auth = localStorage.getItem("auth");
    if (auth) {
      setIsAuthenticated(true);
    }
  }, []);

  return (
    <Router>
      <div>
        <MainLayout isAuthenticated={isAuthenticated} logout={logout} />

        <Routes>
          <Route path="/login" element={<Login login={login} />} />
          <Route
            path="/"
            element={isAuthenticated ? <Home /> : <Navigate to="/login" />}
          />
          <Route
            path="/devices"
            element={isAuthenticated ? <Devices /> : <Navigate to="/login" />}
          />
          <Route
            path="/device-detail/:id"
            element={
              isAuthenticated ? <DeviceDetails /> : <Navigate to="/login" />
            }
          />
          <Route
            path="/add-device"
            element={isAuthenticated ? <AddDevice /> : <Navigate to="/login" />}
          />
          <Route
            path="/users"
            element={isAuthenticated ? <Users /> : <Navigate to="/login" />}
          />
          <Route
            path="/users/:id"
            element={
              isAuthenticated ? <UserDetails /> : <Navigate to="/login" />
            }
          />
          <Route
            path="/add-user"
            element={isAuthenticated ? <AddUser /> : <Navigate to="/login" />}
          />
          <Route
            path="/bills"
            element={isAuthenticated ? <Bills /> : <Navigate to="/login" />}
          />
          <Route
            path="/bills/:id"
            element={
              isAuthenticated ? <BillDetails /> : <Navigate to="/login" />
            }
          />
          <Route
            path="/add-bill"
            element={isAuthenticated ? <AddBill /> : <Navigate to="/login" />}
          />
          <Route
            path="/clients"
            element={isAuthenticated ? <Clients /> : <Navigate to="/login" />}
          />
          <Route
            path="/clients/:id"
            element={
              isAuthenticated ? <ClientDetails /> : <Navigate to="/login" />
            }
          />
          <Route
            path="/add-client"
            element={isAuthenticated ? <AddClient /> : <Navigate to="/login" />}
          />
          <Route
            path="/devices/update/:id"
            element={
              isAuthenticated ? <EditDevice /> : <Navigate to="/login" />
            }
          />
          <Route
            path="/users/update/:id"
            element={isAuthenticated ? <EditUser /> : <Navigate to="/login" />}
          />
          <Route
            path="/clients/update/:id"
            element={
              isAuthenticated ? <EditClient /> : <Navigate to="/login" />
            }
          />
          <Route
            path="/bills/update/:id"
            element={isAuthenticated ? <EditBill /> : <Navigate to="/login" />}
          />
          <Route
            path="/report"
            element={isAuthenticated ? <UploadCsv /> : <Navigate to="/login" />}
          />
          <Route
            path="/device-report/:id"
            element={isAuthenticated ? <ReportDetails /> : <Navigate to="/login" />}
          />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
