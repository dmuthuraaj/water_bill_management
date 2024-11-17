import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

function BillDetails() {
  const { id } = useParams();
  const [bill, setBill] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios
      .get(`http://localhost:9001/bills/${id}`)
      .then((response) => {
        if (response.data.code === 200) {
          setBill(response.data.data);
          setError(null);
        } else {
          setError(response.data.message);
        }
      })
      .catch((error) => {
        setError("Error fetching bill details");
        console.error("Error fetching bill details:", error);
      });
  }, [id]);

  const convertToCSV = () => {
    if (!bill) return "";
    const flattenObj = (obj, parent = "", res = {}) => {
      for (let key in obj) {
        const propName = parent ? `${parent}.${key}` : key;
        if (typeof obj[key] === "object" && obj[key] !== null) {
          flattenObj(obj[key], propName, res);
        } else {
          res[propName] = obj[key];
        }
      }
      return res;
    };

    const flatBill = flattenObj(bill);
    const headers = "Field,Value\n";
    const rows = Object.keys(flatBill)
      .map((key) => `${key},${flatBill[key]}`)
      .join("\n");
    return headers + rows;
  };

  const downloadCSV = () => {
    const csvData = convertToCSV();
    const blob = new Blob([csvData], { type: "text/csv" });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.setAttribute("hidden", "");
    a.setAttribute("href", url);
    a.setAttribute(
      "download",
      `bill_${bill.id}.${bill.consumerDetails.name}.csv`
    );
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
  };

  return (
    <div className="container mt-4">
      <h1>Bill Details</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      {bill ? (
        <div>
          <table className="table table-bordered">
            <tbody>
              <tr>
                <th>Bill ID</th>
                <td>{bill.id}</td>
              </tr>
              <tr>
                <th>Client ID</th>
                <td>{bill.clientId}</td>
              </tr>
              <tr>
                <th>Reading Date</th>
                <td>{bill.readingDate}</td>
              </tr>
              <tr>
                <th>Due Date</th>
                <td>{bill.dueDate}</td>
              </tr>
              <tr>
                <th>Status</th>
                <td>{bill.status}</td>
              </tr>
              <tr>
                <th>Bill Number</th>
                <td>{bill.billNumber}</td>
              </tr>
              <tr>
                <th>Consumer Name</th>
                <td>{bill.consumerDetails?.name}</td>
              </tr>
              <tr>
                <th>Consumer Address</th>
                <td>{bill.consumerDetails?.address}</td>
              </tr>
              <tr>
                <th>RR Number</th>
                <td>{bill.consumerDetails?.rrNumber}</td>
              </tr>
              <tr>
                <th>Present Reading</th>
                <td>{bill.consumptionDetails?.presentReading}</td>
              </tr>
              <tr>
                <th>Previous Reading</th>
                <td>{bill.consumptionDetails?.previousReading}</td>
              </tr>
              <tr>
                <th>Consumption</th>
                <td>{bill.consumptionDetails?.consumption}</td>
              </tr>
              <tr>
                <th>Water Charges</th>
                <td>{bill.billingDetails?.waterCharges}</td>
              </tr>
              <tr>
                <th>Meter Charges</th>
                <td>{bill.billingDetails?.meterCharges}</td>
              </tr>
              <tr>
                <th>Sanitary Charges</th>
                <td>{bill.billingDetails?.sanitaryCharges}</td>
              </tr>
              <tr>
                <th>Other Charges</th>
                <td>{bill.billingDetails?.otherCharges}</td>
              </tr>
              <tr>
                <th>Arrears</th>
                <td>{bill.billingDetails?.arrears}</td>
              </tr>
              <tr>
                <th>Interest</th>
                <td>{bill.billingDetails?.interest}</td>
              </tr>
              <tr>
                <th>Total Amount</th>
                <td>{bill.billingDetails?.totalAmount}</td>
              </tr>
              <tr>
                <th>Advance Amount</th>
                <td>{bill.billingDetails?.advanceAmount}</td>
              </tr>
              <tr>
                <th>Net Amount Due</th>
                <td>{bill.billingDetails?.netAmountDue}</td>
              </tr>
              <tr>
                <th>Previous Month Payment Amount</th>
                <td>{bill.previousMonthPayment?.amount}</td>
              </tr>
              <tr>
                <th>Previous Month Payment Date</th>
                <td>{bill.previousMonthPayment?.payDate || "N/A"}</td>
              </tr>
            </tbody>
          </table>
          <button onClick={downloadCSV} className="btn btn-primary mt-3">
            Export to CSV
          </button>
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
}

export default BillDetails;
