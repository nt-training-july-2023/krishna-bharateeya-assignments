import axios from 'axios';
const BASE_URL = 'http://localhost:8080';

export const RegistrationService = async (userData) => {
    const response = await axios.post(`${BASE_URL}/users/save`, userData);
    return response.data;
};

// export const LoginService = async (email, password) => {

//       const response = await axios.post(`${BASE_URL}/users/login`, {
//         email,
//         password,
//     }); 
//     console.log('ddsfsfsf', response.data);
//       return response.data;
//   };
export const LoadUsers = async () => {

    const response = await axios.get(`${BASE_URL}/users/get/all`);
    return response.data;
}

export const GetUserByEmail=async(email)=>{
    const response = await axios.get(`${BASE_URL}/users/getUser/${email}`);
    return response.data;
}

export const LoadCategories = async () => {
    const response = await axios.get(`${BASE_URL}/category`);
    return response.data;
};

export const LoadQuizzesForCategory = async (categoryId) => {
    const response = await axios.get(`${BASE_URL}/category/quizzes/${categoryId}`);
    return response.data;
};

export const DeleteCategory = async (categoryId) => {
    const response = await axios.delete(`${BASE_URL}/category/delete/${categoryId}`);
    return response.data;
};


export const SaveCategory = async (categoryData, categoryId = null) => {

    let response;
    if (categoryId) {
        response = await axios.put(`${BASE_URL}/category/update/${categoryId}`, categoryData);
        return response.data;
    } else {
        response = await axios.post(`${BASE_URL}/category/save`, categoryData);
        return response.data;
    }
};

export const LoadCategoryById = async (categoryId) => {
    const response = await axios.get(`${BASE_URL}/category/${categoryId}`);
    return response.data;

};

export const GetQuizzes = async () => {
    const response = await axios.get(`${BASE_URL}/quizzes`);
    return response.data;
};

export const DeleteQuiz = async (id) => {
    await axios.delete(`${BASE_URL}/quizzes/${id}`);
};

export const CreateQuiz = async (quizData) => {
    const response = await axios.post(`${BASE_URL}/quizzes`, quizData);
    return response.data;

};

export const UpdateQuiz = async (quizId, quizData) => {
    const response = await axios.put(`${BASE_URL}/quizzes/${quizId}`, quizData);
    return response.data;
};

export const GetQuizById = async (quizId) => {
    const response = await axios.get(`${BASE_URL}/quizzes/${quizId}`);
    return response.data;

};

export const LoadQuestions = async () => {

    const response = await axios.get(`${BASE_URL}/api/questions/all`);
    return response.data;
};

export const AddQuestions = async (questionData) => {
    const response = await axios.post(`${BASE_URL}/api/questions/add`, questionData);
    return response.data;
}

export const UpdateQuestions = async (questionId, questionData) => {
    const response = await axios.put(`${BASE_URL}/api/questions/update/${questionId}`, questionData);
    return response.data;
}

export const DeleteQuestion = async (questionId) => {
    await axios.delete(`${BASE_URL}/api/questions/delete/${questionId}`);
}

export const GetQuestionsById = async (questionId) => {
    const response = await axios.get(`${BASE_URL}/api/questions/${questionId}`);
    return response.data;
}
export const GetQuestionsByQuizId = async (quizId) => {
    const response = await axios.get(`${BASE_URL}/quizzes/questions/${quizId}`);
    return response.data;
}



