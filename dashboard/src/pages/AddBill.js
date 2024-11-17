import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function AddBill() {
  const [clients, setClients] = useState([]);
  const [newBill, setNewBill] = useState({
    clientId: "",
    month: "",
  });
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    // Fetch clients data from API
    axios
      .get("http://localhost:9001/users/client/all")
      .then((response) => {
        if (response.data.code === 200) {
          setClients(response.data.data);
        } else {
          setError("Failed to fetch clients");
        }
      })
      .catch((error) => {
        setError("Error fetching clients");
        console.error("Error fetching clients:", error);
      });
  }, []);

  const handleClientChange = (e) => {
    const selectedClientId = e.target.value;
    const selectedClient = clients.find(
      (client) => client.id === selectedClientId
    );
    if (selectedClient) {
      setNewBill({
        ...newBill,
        clientId: selectedClientId,
        previousReading: parseFloat(selectedClient.previousReading), // Convert to double
        currentReading: parseFloat(selectedClient.currentReading),
        totalBill: parseFloat(selectedClient.previousReading * 10), // Calculate as a double
      });
    }
  };

  const handleAddBill = () => {
    axios
      .post("http://localhost:9001/bills/add", newBill)
      .then((response) => {
        if (response.data.code === 200) {
          navigate("/bills");
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => {
        setError("Error adding bill");
        console.error("Error adding bill:", error);
      });
  };

  return (
    <div className="container mt-4">
      <h1>Add New Bill</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleAddBill();
        }}
      >
        <div className="mb-3">
          <label htmlFor="clientId" className="form-label">
            Client ID
          </label>
          <select
            id="clientId"
            className="form-select"
            value={newBill.clientId}
            onChange={handleClientChange}
            required
          >
            <option value="">Select a client</option>
            {clients.map((client) => (
              <option key={client.id} value={client.id}>
                {client.id} - {client.name}
              </option>
            ))}
          </select>
        </div>
        <div className="mb-3">
          <label htmlFor="month" className="form-label">
            Month
          </label>
          <input
            type="text"
            id="month"
            className="form-control"
            value={newBill.month}
            onChange={(e) => setNewBill({ ...newBill, month: e.target.value })}
            required
          />
        </div>
        <button type="submit" className="btn btn-success">
          Add Bill
        </button>
      </form>
    </div>
  );
}

export default AddBill;
