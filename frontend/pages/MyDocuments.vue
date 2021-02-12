<template>
    <div>
        <h2>Мои документы</h2>

        <h3>Добавить файл:</h3>
        <form>
            <input type="file" name="file" id="file"><br/>
            <input type="button" value="Добавить" @click="uploadFile()">
        </form>

        <ul>
            <li v-for="file in getFiles"><a :href="'/files/'+file.filename" :download="file.shortName">{{file.shortName}}</a></li>
        </ul>



    </div>
</template>

<script>
    import {mapGetters, mapActions} from "vuex";

    export default {

        computed: mapGetters(['getProfile', 'getFiles']),

        methods: {
            ...mapActions(['getFilesAction', 'addFileAction']),

            uploadFile() {
                const file = document.getElementById('file').files[0];
                this.addFileAction({
                                    file: file,
                                    user: this.getProfile
                })
            }
        },

        created(){
            this.getFilesAction(this.getProfile)
        }
    }
</script>

<style scoped>

    h2 {
        text-align: center;
    }
    h3 {
        text-align: left;
    }

    li {
        padding: 1em;
    }

</style>