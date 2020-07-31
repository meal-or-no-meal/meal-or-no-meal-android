package edu.cnm.deepdive.mealornomeal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import edu.cnm.deepdive.mealornomeal.LoginActivity;
import edu.cnm.deepdive.mealornomeal.R;
import edu.cnm.deepdive.mealornomeal.service.GoogleSignInService;


public class MainActivity extends AppCompatActivity {

  private GoogleSignInService signInService;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setupObservers();
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.options, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    boolean handled = true;
    //noinspection SwitchStatementWithTooFewBranches
    switch (item.getItemId()) {
      case R.id.sign_out:
        signInService.signOut().addOnCompleteListener((ignored) -> switchToLogin());
        break;
      default:
        handled = super.onOptionsItemSelected(item);
    }
    return handled;
  }

  private void setupObservers() {
    signInService = GoogleSignInService.getInstance();
    signInService.getAccount().observe(this, (account) -> {
      if (account == null) {
        switchToLogin();
      }
    });
  }

  private void switchToLogin() {
    Intent intent = new Intent(this, LoginActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }


}

