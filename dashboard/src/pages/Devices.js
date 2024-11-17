// src/pages/Devices.js

import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

function Devices() {
  const [devices, setDevices] = useState([]);
  // const [file, setFile] = useState(null);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get("http://localhost:9001/devices/all")
      .then((response) => {
        if (response.data.code === 200) {
          setDevices(response.data.data);
          setError(null);
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => {
        setError("Error fetching devices");
        console.error("Error fetching devices:", error);
      });
  }, []);

  const handleEdit = (deviceId) => {
    navigate(`/devices/update/${deviceId}`);
  };

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h1>Devices</h1>
        <Link to="/add-device" className="btn btn-primary">
          Add Device
        </Link>
      </div>

      {error && <div className="alert alert-danger">{error}</div>}
      <table className="table table-striped">
        <thead>
          <tr>
            <th>#</th> {/* Serial Number Column */}
            <th>Serial Number</th>
            <th>HHU ID</th>
            <th>Created At</th>
            <th>Active</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {devices.length > 0 ? (
            devices.map((device, index) => (
              <tr key={device.id}>
                <td>{index + 1}</td> {/* Serial Number */}
                <td>{device.serialNumber}</td>
                <td>{device.hhuId}</td>
                <td>{device.createdAt}</td>
                <td>
                  <span
                    style={{
                      color: device.active ? "green" : "red",
                      fontWeight: "bold",
                    }}
                  >
                    {device.active ? "Yes" : "No"}
                  </span>
                </td>
                <td>
                  <button
                    className="btn btn-info btn-sm"
                    onClick={() => navigate(`/device-detail/${device.id}`)}
                  >
                    View
                  </button>
                  <button
                    className="btn btn-warning btn-sm"
                    onClick={() => handleEdit(device.id)}
                  >
                    Edit
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5">No devices available</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default Devices;
