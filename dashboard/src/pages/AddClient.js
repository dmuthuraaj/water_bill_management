// src/pages/AddClient.js

import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function AddClient() {
  const [newClient, setNewClient] = useState({
    category: "",
    name: "",
    mobileNumber: "",
    address: "",
    meterSerialNumber: "",
    firstReading: 0,
  });
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleAddClient = () => {
    axios
      .post("http://13.232.33.97/users/client", newClient)
      .then((response) => {
        if (response.data.code === 200) {
          alert(response.data.message);
          navigate("/clients");
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => {
        setError("Error adding client: "+error.response.data.message);
        // console.error("Error adding client:", error.response.data.message);
      });
  };

  return (
    <div className="container mt-4">
      <h1>Add New client</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleAddClient();
        }}
      >
        <div className="mb-3">
          <label htmlFor="category" className="form-label">
            Category
          </label>
          <input
            type="text"
            id="category"
            className="form-control"
            value={newClient.category}
            onChange={(e) =>
              setNewClient({ ...newClient, category: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="name" className="form-label">
            Name
          </label>
          <input
            type="text"
            id="name"
            className="form-control"
            value={newClient.name}
            onChange={(e) =>
              setNewClient({ ...newClient, name: e.target.value })
            }
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
            value={newClient.mobileNumber}
            onChange={(e) =>
              setNewClient({ ...newClient, mobileNumber: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="address" className="form-label">
            Address
          </label>
          <input
            type="text"
            id="address"
            className="form-control"
            value={newClient.address}
            onChange={(e) =>
              setNewClient({ ...newClient, address: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="meterSerialNumber" className="form-label">
            Meter SerialNumber
          </label>
          <input
            type="number"
            id="meterSerialNumber"
            className="form-control"
            value={newClient.meterSerialNumber}
            onChange={(e) =>
              setNewClient({ ...newClient, meterSerialNumber: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="firstReading" className="form-label">
            First Reading
          </label>
          <input
            type="number"
            id="firstReading"
            className="form-control"
            value={newClient.firstReading}
            onChange={(e) =>
              setNewClient({
                ...newClient,
                firstReading: parseInt(e.target.value),
              })
            }
            required
          />
        </div>
        <button type="submit" className="btn btn-success">
          Add client
        </button>
      </form>
    </div>
  );
}

export default AddClient;
