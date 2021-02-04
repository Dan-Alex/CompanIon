import departmentsApi from "../../resource/api/departments";

export default {

    state:{
        departments: []
    },

    getters: {
        getAllDepartments: state => state.departments,
    },

    mutations:{

        getAllDepartmentsMutation(state, departments){
            state.departments = departments
        },

        addDepartmentMutation(state, department){
            state.departments = [
                ...state.departments,
                department
            ]
        },

        updateDepartmentMutation(state, department){
            const updIndex = state.departments.findIndex(i => i.id === department.id);
            state.departments = [
                ...state.departments.slice(0, updIndex),
                department,
                ...state.departments.slice(updIndex+1)
            ]
        },

        deleteDepartmentMutation(state, department){
            const delIndex = state.departments.findIndex(i => i.id === department.id);
            state.departments = [
                ...state.departments.slice(0, delIndex),
                ...state.departments.slice(delIndex+1)
            ]
        },

    }
    ,
    actions: {

        async getAllDepartmentsAction({commit}){
            const result = await departmentsApi.getAll();
            const data = await result.json();
            if (result.ok){
                commit('getAllDepartmentsMutation', data)
            }
        },

        async addDepartmentAction({commit}, department){
            const result = await departmentsApi.add(department);
            const data = await result.json();
            if (result.ok) {
                commit('addDepartmentMutation', data)
            }
        },

        async updateDepartmentAction({commit}, department){
            const result = await departmentsApi.update(department);
            const data = await result.json();
            commit('updateDepartmentMutation', data)
        },

        async deleteDepartmentAction({commit}, department){
            const result = await departmentsApi.delete(department);
            if (result.ok) {
                commit('deleteDepartmentMutation', department)
            }
        },


    }
}