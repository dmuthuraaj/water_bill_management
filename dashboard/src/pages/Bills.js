import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

function Bills() {
  const [bills, setBills] = useState([]);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get("http://localhost:9001/bills/all")
      .then((response) => {
        if (response.data.code === 200) {
          setBills(response.data.data);
          setError(null);
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => {
        setError("Error fetching bills");
        console.error("Error fetching bills:", error);
      });
  }, []);

  const handleEdit = (billId) => {
    navigate(`/bills/update/${billId}`);
  };

  return (
    <div className="container">
      <div className="d-flex justify-content-between align-items-center">
        <h1>List of Bills</h1>
        <Link to="/add-bill" className="btn btn-primary">
          Add Bill
        </Link>
      </div>
      {error && <div className="alert alert-danger">{error}</div>}
      <table className="table table-striped mt-3">
        <thead>
          <tr>
            <th>#</th>
            <th>Client Id</th>
            <th>Amount</th>
            <th>Reading Date</th>
            <th>Due Date</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {bills.length > 0 ? (
            bills.map((bill, index) => (
              <tr key={bill.id}>
                <td>{index + 1}</td>
                <td>{bill.clientId}</td>
                <td>{bill.totalAmount}</td>
                <td>{bill.readingDate}</td>
                <td>{bill.dueDate}</td>
                <td>{bill.status}</td>
                <td>
                  <button
                    className="btn btn-info btn-sm"
                    onClick={() => navigate(`/bills/${bill.id}`)}
                  >
                    View
                  </button>
                  <button
                    className="btn btn-warning btn-sm"
                    onClick={() => handleEdit(bill.id)}
                  >
                    Edit
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5">No bills available</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default Bills;
