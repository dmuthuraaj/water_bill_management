import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function AddBill() {
  const [clients, setClients] = useState([]);
  const [newBill, setNewBill] = useState({
    clientId: "",
    previousReading: 0.0, // Initialize as a double
    readingDate: "",
    currentReading: 0.0,
    dueDate: "",
    rate: 10.0,
    totalBill: 0.0, // Initialize as a double
    status: "",
  });
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    // Fetch clients data from API
    axios
      .get("http://13.232.33.97/users/client/all")
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
    const selectedClient = clients.find(client => client.id === selectedClientId);
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
      .post("http://13.232.33.97/bills/add", newBill)
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
          <label htmlFor="previousReading" className="form-label">
            Previous Reading
          </label>
          <input
            type="number"
            id="previousReading"
            className="form-control"
            value={newBill.previousReading}
            readOnly
          />
        </div>
        <div className="mb-3">
          <label htmlFor="readingDate" className="form-label">
            Reading Date
          </label>
          <input
            type="date"
            id="readingDate"
            className="form-control"
            value={newBill.readingDate}
            onChange={(e) =>
              setNewBill({ ...newBill, readingDate: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="currentReading" className="form-label">
            Current Reading
          </label>
          <input
            type="number"
            id="currentReading"
            className="form-control"
            value={newBill.currentReading}
            onChange={(e) =>
              setNewBill({ ...newBill, currentReading: parseFloat(e.target.value) })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="rate" className="form-label">
            Rate
          </label>
          <input
            type="number"
            id="rate"
            className="form-control"
            value={newBill.rate}
            onChange={(e) => setNewBill({ ...newBill, rate: parseFloat(e.target.value) })} // Convert to double
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="totalBill" className="form-label">
            Total Bill
          </label>
          <input
            type="number"
            id="totalBill"
            className="form-control"
            value={newBill.totalBill}
            readOnly
          />
        </div>
        <div className="mb-3">
          <label htmlFor="dueDate" className="form-label">
            Due Date
          </label>
          <input
            type="date"
            id="dueDate"
            className="form-control"
            value={newBill.dueDate}
            onChange={(e) =>
              setNewBill({ ...newBill, dueDate: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="status" className="form-label">
            Status
          </label>
          <select
            id="status"
            className="form-select"
            value={newBill.status}
            onChange={(e) => setNewBill({ ...newBill, status: e.target.value })}
            required
          >
            <option value="">Select Status</option>
            <option value="PENDING">PENDING</option>
            <option value="PAID">PAID</option>
          </select>
        </div>
        <button type="submit" className="btn btn-success">
          Add Bill
        </button>
      </form>
    </div>
  );
}

export default AddBill;
