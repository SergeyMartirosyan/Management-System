document.addEventListener('DOMContentLoaded', fetchData);

function fetchData() {
  // Access the data from local storage
  const token = localStorage.getItem('token');
  // Replace 'YOUR_SERVER_URL' with your actual server URL
  fetch('http://localhost:8080/api/v1/lighter', {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
      // Add any additional headers if required
    },
  })
  .then(response => response.json())
  .then(data => displayData(data))
  .catch(error => console.error('Error fetching data:', error));
}

function displayData(data) {
  // Access the keys 'name' and 'price' from the data and create table rows
  const tableBody = document.querySelector('#data-table tbody');

  // Clear existing rows
  tableBody.innerHTML = '';

  data.forEach(item => {
    const row = tableBody.insertRow();
    const cellName = row.insertCell(0);
    const cellPrice = row.insertCell(1);
    const cellModify = row.insertCell(2);

    cellName.textContent = item.name;
    cellPrice.textContent = item.price;

    // Create a button for deletion
    const deleteButton = document.createElement('button');
    deleteButton.textContent = 'Delete';
    deleteButton.addEventListener('click', () => {
        deleteItem(item.id);
    }); // Assuming there is an 'id' property in your data

    // Append the button to the cell

    // Create a button for updating
    const updateButton = document.createElement('button');

    updateButton.textContent = 'Update';
    updateButton.addEventListener('click', () => {
      updateButton.classList.add('hidden'); // Initially hide the Update button
      // Turn the name and price columns into input areas
      cellName.innerHTML = `<input type="text" value="${item.name}" />`;
      cellPrice.innerHTML = `<input type="text" value="${item.price.replace(/\$/g, '')}" />`;

      // Create a "Finish" button for finalizing the update
      const finishButton = document.createElement('button');
      finishButton.textContent = 'Finish';
      finishButton.addEventListener('click', () => {
        const newName = cellName.querySelector('input').value;
        const newPrice = cellPrice.querySelector('input').value;

        // Validate inputs before sending the PUT request
        if (newName && !isNaN(newPrice)) {
          updateItem(item.id, { name: newName, price: '$'+newPrice });
        } else {
          alert('Please fill out both name and enter a valid numeric price.');
        }
      });

      // Append the "Finish" button to the cell
      cellModify.appendChild(finishButton);
    });

    // Append the buttons to the cell
    cellModify.appendChild(updateButton);
    cellModify.appendChild(deleteButton);
  });
}


function deleteItem(itemId) {
  const token = localStorage.getItem('token');
  // Replace 'YOUR_SERVER_URL' with your actual server URL
  fetch(`http://localhost:8080/api/v1/lighter/${itemId}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
      // Add any additional headers if required
    },
  })
  .then(response => {
    if (response.ok) {
      // Item deleted successfully, fetch updated data and refresh the table
      fetchData();
      console.log(`Item with ID ${itemId} deleted successfully`);
    } else {
      console.error('Error deleting item:', response.status);
    }
  })
  .catch(error => console.error('Error deleting item:', error));
}

function updateItem(itemId, updatedProduct) {
  const token = localStorage.getItem('token');
    // Replace 'YOUR_SERVER_URL' with your actual server URL
    fetch(`http://localhost:8080/api/v1/lighter/${itemId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(updatedProduct),
  })
  .then(response => {
    if (response.ok) {
      // Item created successfully, fetch updated data and refresh the table
      fetchData();
      console.log('Item successfully updated');
    } else {
      console.error('Error updating item:', response.status);
    }
  })
  .catch(error => console.error('Error updating item:', error));
}

function addNewRow() {
  const createNewRow = document.getElementById('CreateNewRow')
  createNewRow.style.display = 'none';
  const tableBody = document.querySelector('#data-table tbody');
  const newRow = tableBody.insertRow();
  const cellName = newRow.insertCell(0);
  const cellPrice = newRow.insertCell(1);
  const cellCreate = newRow.insertCell(2);

  // Create input fields for 'name' and 'price'
  const inputName = document.createElement('input');
  const inputPrice = document.createElement('input');

  cellName.appendChild(inputName);
  cellPrice.appendChild(inputPrice);

  // Create a button for creating a new row
  const createButton = document.createElement('button');
  createButton.textContent = 'Create';
  createButton.addEventListener('click', () => {
    const newName = inputName.value;
    const newPrice = inputPrice.value;

    // Validate inputs before sending the POST request
    if (newName && !isNaN(newPrice)) {
      createNewItem({ name: newName, price: '$'+newPrice });
    } else {
      alert('Please fill out both name and enter a valid numeric price.');
    }
    createNewRow.style.display = 'block';
  });

  // Append the button to the cell
  cellCreate.appendChild(createButton);
}

function createNewItem(newItem) {
  const token = localStorage.getItem('token');
  // Replace 'YOUR_SERVER_URL' with your actual server URL
  fetch('http://localhost:8080/api/v1/lighter', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify(newItem),
  })
  .then(response => {
    if (response.ok) {
      // Item created successfully, fetch updated data and refresh the table
      fetchData();
      console.log('Item created successfully');
    } else {
      console.error('Error creating item:', response.status);
    }
  })
  .catch(error => console.error('Error creating item:', error));
}

function logout() {
  // Assuming you want to redirect to a login page named 'login.html'
  window.location.href = 'http://127.0.0.1:5500/Front-end2/login.html';
}

