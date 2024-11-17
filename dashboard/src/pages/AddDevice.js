// src/pages/AddDevice.js

import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function AddDevice() {
  const [newDevice, setNewDevice] = useState({
    serialNumber: '',
    hhuId: '',
  });
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleAddDevice = () => {
    axios.post('http://localhost:9001/devices/add', newDevice)
      .then(response => {
        if (response.data.code === 200) {
          navigate('/devices');
        } else {
          setError(response.data.message);
        }
      })
      .catch(error => {
        setError('Error adding device');
        console.error('Error adding device:', error);
      });
  };

  return (
    <div className="container mt-4">
      <h1>Add New Device</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      <form onSubmit={e => { e.preventDefault(); handleAddDevice(); }}>
        <div className="mb-3">
          <label htmlFor="serialNumber" className="form-label">Serial Number</label>
          <input
            type="text"
            id="serialNumber"
            className="form-control"
            value={newDevice.serialNumber}
            onChange={e => setNewDevice({ ...newDevice, serialNumber: e.target.value })}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="hhuId" className="form-label">HHU ID</label>
          <input
            type="text"
            id="hhuId"
            className="form-control"
            value={newDevice.hhuId}
            onChange={e => setNewDevice({ ...newDevice, hhuId: e.target.value })}
            required
          />
        </div>
        <button type="submit" className="btn btn-success">Add Device</button>
      </form>
    </div>
  );
}

export default AddDevice;
