// src/pages/AddUser.js

import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function AddUser() {
  const [newUser, setNewUser] = useState({
    name: "",
    mobileNumber: "",
    email: "",
    password: "",
    role: "",
    isActive: true,
  });
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleAddUser = () => {
    axios
      .post("http://localhost:9001/users/add", newUser)
      .then((response) => {
        if (response.data.code === 200) {
          navigate("/users");
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => {
        setError("Error adding user");
        console.error("Error adding user:", error);
      });
  };

  return (
    <div className="container mt-4">
      <h1>Add New user</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleAddUser();
        }}
      >
        <div className="mb-3">
          <label htmlFor="name" className="form-label">
            Name
          </label>
          <input
            type="text"
            id="name"
            className="form-control"
            value={newUser.name}
            onChange={(e) => setNewUser({ ...newUser, name: e.target.value })}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="mobileNumber" className="form-label">
            Mobile Number
          </label>
          <input
            type="text"
            id="mobileNumber"
            className="form-control"
            value={newUser.mobileNumber}
            onChange={(e) =>
              setNewUser({ ...newUser, mobileNumber: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">
            Email
          </label>
          <input
            type="email"
            id="email"
            className="form-control"
            value={newUser.email}
            onChange={(e) => setNewUser({ ...newUser, email: e.target.value })}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="password" className="form-label">
            Password
          </label>
          <input
            type="password"
            id="password"
            className="form-control"
            value={newUser.password}
            onChange={(e) =>
              setNewUser({ ...newUser, password: e.target.value })
            }
            required
          />
        </div>
        {/* <div className="mb-3">
          <label htmlFor="role" className="form-label">Role</label>
          <select
            id="role"
            className="form-select"
            value={newUser.role}
            onChange={e => setNewUser({ ...newUser, role: e.target.value === "ADMIN" })}
          >
            <option value="ADMIN">ADMIN</option>
            <option value="USER">USER</option>
          </select>
        </div> */}
        <div className="mb-3">
          <label htmlFor="role" className="form-label">
            Role
          </label>
          <select
            id="role"
            className="form-select"
            value={newUser.role}
            onChange={(e) => setNewUser({ ...newUser, role: e.target.value })}
          >
            <option value="">Please select a role</option>
            <option value="ADMIN">ADMIN</option>
            <option value="USER">USER</option>
          </select>
        </div>
        <button type="submit" className="btn btn-success">
          Add user
        </button>
      </form>
    </div>
  );
}

export default AddUser;
