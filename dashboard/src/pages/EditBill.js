import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";

function EditBill() {
  const { id } = useParams();
  const [bill, setBill] = useState(null);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(`http://13.232.33.97/bills/${id}`)
      .then((response) => setBill(response.data.data))
      .catch((error) => setError("Error fetching bill details"));
  }, [id]);

  const handleUpdateBill = () => {
    axios
      .put(`http://13.232.33.97/bills/${id}`, bill)
      .then((response) => {
        if (response.data.code === 200) {
          navigate("/bills");
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => setError("Error updating bill"));
  };

  if (!bill) return <div>Loading...</div>;

  return (
    <div className="container mt-4">
      <h1>Update Bill</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleUpdateBill();
        }}
      >
        <div className="mb-3">
          <label htmlFor="id" className="form-label">
            Bill ID
          </label>
          <input
            type="text"
            id="id"
            className="form-control"
            value={bill.id}
            onChange={(e) => setBill({ ...bill, id: e.target.value })}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="clientId" className="form-label">
            Client ID
          </label>
          <input
            type="text"
            id="clientId"
            className="form-control"
            value={bill.clientId}
            onChange={(e) => setBill({ ...bill, clientId: e.target.value })}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="totalBill" className="form-label">
            Total Amount
          </label>
          <input
            type="text"
            id="totalBill"
            className="form-control"
            value={bill.totalBill}
            onChange={(e) => setBill({ ...bill, totalBill: e.target.value })}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="readingDate" className="form-label">
            Reading Date
          </label>
          <input
            type="text"
            id="readingDate"
            className="form-control"
            value={bill.readingDate}
            onChange={(e) => setBill({ ...bill, readingDate: e.target.value })}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="dueDate" className="form-label">
            Due Date
          </label>
          <input
            type="text"
            id="dueDate"
            className="form-control"
            value={bill.dueDate}
            onChange={(e) => setBill({ ...bill, dueDate: e.target.value })}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="status" className="form-label">
            Status
          </label>
          <input
            type="text"
            id="status"
            className="form-control"
            value={bill.status}
            onChange={(e) => setBill({ ...bill, status: e.target.value })}
            required
          />
        </div>
        <button type="submit" className="btn btn-success">
          Update Bill
        </button>
      </form>
    </div>
  );
}

export default EditBill;
