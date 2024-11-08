import React, { useState } from "react";
import axios from "axios";

function UploadCsv() {
  const [selectedFile, setSelectedFile] = useState(null);
  const [message, setMessage] = useState("");

  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  const handleUpload = () => {
    if (!selectedFile) {
      setMessage("Please select a file to upload.");
      return;
    }

    const formData = new FormData();
    formData.append("csvFile", selectedFile);

    axios.post("http://13.232.33.97/devices/report/upload", formData)
      .then((response) => {
        if (response.data.code === 200) {
          setMessage("File uploaded successfully.");
        } else {
          setMessage(response.data.message || "Failed to upload file.");
        }
      })
      .catch((error) => {
        setMessage("Error uploading file.");
        console.error("Error uploading file:", error);
      });
  };

  return (
    <div className="container mt-4">
      <h1>Upload CSV File</h1>
      {message && <div className="alert alert-info">{message}</div>}
      <div className="mb-3">
        <input
          type="file"
          className="form-control"
          onChange={handleFileChange}
          accept=".csv"
        />
      </div>
      {/* Note for uploading only yearly reports */}
      <div className="mb-3" style={{ color: 'red' }}>
        <strong>*Note:</strong> Only yearly reports can be uploaded for now.
      </div>
      <button
        className="btn btn-primary"
        onClick={handleUpload}
      >
        Upload
      </button>
    </div>
  );
}

export default UploadCsv;
