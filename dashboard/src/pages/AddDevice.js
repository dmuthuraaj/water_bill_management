// src/pages/AddDevice.js

import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function AddDevice() {
  const [newDevice, setNewDevice] = useState({
    serialNumber: '',
    clientId: '',
    deviceId: '',
    isActive: true
  });
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleAddDevice = () => {
    axios.post('http://13.232.33.97/devices/add', newDevice)
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
          <label htmlFor="deviceId" className="form-label">Device ID</label>
          <input
            type="text"
            id="deviceId"
            className="form-control"
            value={newDevice.deviceId}
            onChange={e => setNewDevice({ ...newDevice, deviceId: e.target.value })}
            required
          />
        </div>
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
          <label htmlFor="clientId" className="form-label">Client ID</label>
          <input
            type="text"
            id="clientId"
            className="form-control"
            value={newDevice.userId}
            onChange={e => setNewDevice({ ...newDevice, clientId: e.target.value })}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="isActive" className="form-label">Active</label>
          <select
            id="isActive"
            className="form-select"
            value={newDevice.isActive}
            onChange={e => setNewDevice({ ...newDevice, isActive: e.target.value === 'true' })}
          >
            <option value="true">Yes</option>
            <option value="false">No</option>
          </select>
        </div>
        <button type="submit" className="btn btn-success">Add Device</button>
      </form>
    </div>
  );
}

export default AddDevice;
