import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';



const CategoryHome = () => {
    const [categories, setCategories] = useState([]);

    const { categoryId } = useParams();

    useEffect(() => {
        loadCategories();
    }, []);

    const loadCategories = async () => {
        const result = await axios.get('http://localhost:8080/category');
        setCategories(result.data);
    };

    const deleteCategories = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/category/delete/${id}`);
            loadCategories();

        } catch (error) {
            console.log(error.response.data.message || 'An error occurred. Please try again.');
        }
    };


    return (
        <div className="wrapper">

            <div className="container">
                <div className="card" style={{ boxShadow: '3px 3px 5px 5px #939fd4', marginTop: '20px' }}>
                    <div className="card-header">
                        <h3>Manage Categories</h3>
                        <center>
                            <Link className='button btn-user mx-3' to={`\addCategory`}>Add Category</Link>
                        </center>
                    </div>

                    <div className="card-body">
                        <table className="table">
                            <thead className="table-secondary">
                                <tr>
                                    <th scope="col"><center>S.No.</center></th>
                                    <th scope="col"><center>Name</center></th>
                                    <th scope="col"><center>Description</center></th>
                                    <th scope="col"><center>Actions</center></th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    categories.map((category, index) => (
                                        <tr key={index} >
                                            <th scope="row" key={index}><center>{index + 1}</center></th>
                                            <td><center>{category.categoryName}</center></td>
                                            <td>
                                            <center>{category.description}</center>
                                            </td>
                                            <td><center>
                                                <Link className="button button-edit mx-2" to={`/editcategory/${''}`}> Update</Link>
                                                <button className="button button-delete" onClick={() => deleteCategories(category.categoryId)}>Delete</button>
                                            </center>
                                            </td>
                                            <td>

                                            </td>
                                        </tr>
                                    ))}

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>


        </div>
    );

}

export default CategoryHome;
