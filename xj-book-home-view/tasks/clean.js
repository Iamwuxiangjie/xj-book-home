import gulp from "gulp";
import del from "del";

gulp.task('clean', () => {
  return del("src/main/resources/static/*");
});
