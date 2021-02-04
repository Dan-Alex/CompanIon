import companyAPI from "../../api/company"

export default {
    state: {
        company: {}
    },

    getters: {
        getCompany: state => state.company,
    },

    mutations: {
        companyMutation(state, company) {
            state.company = company
        }
    },

    actions: {
        async getCompanyAction({commit}) {
            const result = await companyAPI.get();
            const data = await result.json();
            if (result.ok) {
                commit('companyMutation', data)
            }
        },

        async putCompanyAction({commit}, company) {
            const result = await companyAPI.update(company);
            const data = await result.json();
            if (result.ok) {
                commit('companyMutation', data)
            }
        }
    }
}