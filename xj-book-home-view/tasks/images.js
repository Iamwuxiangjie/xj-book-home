import gulp from "gulp";
import gulpif from "gulp-if";
import livereload from "gulp-livereload";
import args from "./lib/args";

gulp.task('images', () => {
  return gulp.src('app/images/**/*')
  // .pipe(gulpif(args.production, imagemin()))
    .pipe(gulp.dest("src/main/resources/static/images"))
    .pipe(gulp.dest("target/classes/static/images"))
    .pipe(gulpif(args.watch, livereload({
      port: 7772
    })));
});
