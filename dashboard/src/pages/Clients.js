// src/pages/Clients.js

import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

function Clients() {
  const [clients, setClients] = useState([]);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get("http://13.232.33.97/users/client/all")
      .then((response) => {
        if (response.data.code === 200) {
          setClients(response.data.data);
          setError(null);
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => {
        setError("Error fetching clients");
        console.error("Error fetching clients:", error);
      });
  }, []);

  const handleEdit = (clientId) => {
    navigate(`/clients/update/${clientId}`);
  };

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h1>Clients</h1>
        <Link to="/add-client" className="btn btn-primary">
          Add Client
        </Link>
      </div>

      {error && <div className="alert alert-danger">{error}</div>}
      <table className="table table-striped">
        <thead>
          <tr>
            <th>#</th> {/* Serial Number Column */}
            <th>Client ID</th>
            <th>Meter Serial</th>
            <th>Category</th>
            <th>Created At</th>
            <th>Active</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {clients.length > 0 ? (
            clients.map((client, index) => (
              <tr key={client.id}>
                <td>{index + 1}</td>
                <td>{client.id}</td>
                <td>{client.meterSerialNumber}</td>
                <td>{client.category}</td>
                <td>{client.createdAt}</td>
                <td>
                  <span
                    style={{
                      color: client.active ? "green" : "red",
                      fontWeight: "bold",
                    }}
                  >
                    {client.active ? "Yes" : "No"}
                  </span>
                </td>
                <td>
                  <button
                    className="btn btn-info btn-sm"
                    onClick={() => navigate(`/clients/${client.id}`)}
                  >
                    View
                  </button>
                  <button
                    className="btn btn-warning btn-sm"
                    onClick={() => handleEdit(client.id)}
                  >
                    Edit
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="7">No clients available</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default Clients;
