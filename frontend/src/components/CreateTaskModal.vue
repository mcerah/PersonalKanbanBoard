<template>
  <div class="modal">
    <!-- Modal content -->
    <div class="modal-content">
      <form @submit="createTask($event)">
        <div class="title-container">
          <h2>Create A Task</h2>
        </div>
        <a class="close" @click="handleClick"/>
        <hr>
        <div class="input-container">
          <label for="ttitle">TITLE :</label>
          <input
            type="text"
            id="ttitle"
            name="tasktitle"
            placeholder="Task Title"
            class="modal-input"
            v-model="newTask.title"
            required
          >

          <label for="desctask">DESCRIPTION :</label>
          <textarea
            type="text"
            id="dtask"
            name="desctask"
            placeholder="Describe the task ..."
            class="modal-input"
            v-model="newTask.description"
            required
          ></textarea>

          <label for="ddate">DUE DATE :</label>
          <input
            type="date"
            id="ddate"
            name="duedate"
            class="modal-input"
            v-model="newTask.dueDate"
            required
          >
        </div>
        <hr>
        <div class="modal-button-container">
          <button class="modal-button cancel-button" @click="handleClick">Cancel</button>
          <button class="modal-button ok-button">OK</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class CreateTaskModal extends Vue {
  @Prop() private msg!: string;
  newTask: object = { title: "", description: "", dueDate: "" };
  handleClick() {
    this.$emit("click");
  }
  createTask(event: object) {
    console.log(this.newTask);
    this.$emit("createTask", event, this.newTask);
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
hr {
  margin-right: -20px;
  margin-left: -20px;
  border: 0;
  border-top: 1px solid #ded8d8;
}
.modal-title {
  float: left;
}

.modal-button-container {
  text-align: right;
}

.modal-button {
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 10px;
  font-size: 16px;
}

.ok-button {
  background-color: dodgerblue;
}
.cancel-button {
  background-color: #888;
}

h2 {
  display: flex;
  margin-bottom: 25px;
  margin-top: 10px;
}

.modal-input {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-bottom: 20px;
}

.input-container {
  display: block;
  text-align: left;
  margin-top: 25px;
}
.modal {
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  background-color: #fefefe;
  margin: auto;
  padding: 20px 20px 10px 20px;
  border: 1px solid #888;
  width: 500px;
  border-radius: 5px;
  position: relative;
}

/* The Close Button */
.close {
  color: #aaaaaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

.close {
  position: absolute;
  right: 15px;
  top: 30px;
  width: 32px;
  height: 32px;
  opacity: 0.6;
}
.close:hover {
  opacity: 1;
}
.close:before,
.close:after {
  position: absolute;
  left: 15px;
  content: " ";
  height: 20px;
  width: 2px;
  background-color: #333;
}
.close:before {
  transform: rotate(45deg);
}
.close:after {
  transform: rotate(-45deg);
}
</style>
