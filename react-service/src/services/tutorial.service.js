import http from "../http-common";

class TutorialDataService {
  getAll() {
    return http.get("/tutorials/all");
  }

  get(id) {
    return http.get(`/tutorials/${id}`);
  }

  create(data) {
    return http.post("/tutorials/", data);
  }

  update(id, data) {
    return http.patch(`/tutorials/${id}`, data);
  }

  delete(id) {
    return http.delete(`/tutorials/${id}`);
  }

  deleteAll() {
    return http.delete(`/tutorials/deleteAll`);
  }

  findByTitle(title) {
    return http.get(`/tutorials/all?title=${title}`);
  }
}

export default new TutorialDataService();